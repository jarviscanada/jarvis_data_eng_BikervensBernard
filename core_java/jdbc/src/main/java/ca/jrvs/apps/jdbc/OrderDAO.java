package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {

    final Logger logger = LoggerFactory.getLogger (OrderDAO.class);
    private final static String GET_BY_ID = "SELECT\n"
            + "  c.first_name, c.last_name, c.email, o.order_id,\n"
            + "  o.creation_date, o.total_due, o.status,\n"
            + "  s.first_name, s.last_name, s.email,\n"
            + "  ol.quantity,\n"
            + "  p.code, p.name, p.size, p.variety, p.price\n"
            + "from orders o\n"
            + "  join customer c on o.customer_id = c.customer_id\n"
            + "  join salesperson s on o.salesperson_id=s.salesperson_id\n"
            + "  join order_item ol on ol.order_id=o.order_id\n"
            + "  join product p on ol.product_id = p.product_id\n"
            + "where o.order_id = ?";

    private static final String GET_FOR_CUST = "SELECT * FROM get_orders_by_customer(?)";

    public OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Order findById(long id) {
        Order order = new Order();
        try (PreparedStatement statement = this.connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            long orderId = 0;
            List<OrderLine> orderLines = new ArrayList<>();
            while (rs.next()) {
                if (orderId == 0) {
                    order.setCustomerFirstName(rs.getString(1));
                    order.setCustomerLastLane(rs.getString(2));
                    order.setCustomerEmail(rs.getString(3));
                    order.setId(rs.getLong(4));
                    orderId = order.getId();
                    order.setCreationDate(new Date(rs.getDate(5).getTime()));
                    order.setTotalDue(rs.getBigDecimal(6));
                    order.setStatus(rs.getString(7));
                    order.setSalespersonFirstName(rs.getString(8));
                    order.setSalespersonLastName(rs.getString(9));
                    order.setSalespersonEmail(rs.getString(10));

                }
                OrderLine orderLine = new OrderLine();
                orderLine.setQuantity(rs.getInt(11));
                orderLine.setProductCode(rs.getString(12));
                orderLine.setProductName(rs.getString(13));
                orderLine.setProductSize(rs.getInt(14));
                orderLine.setProductVariety(rs.getString(15));
                orderLine.setProductPrice(rs.getBigDecimal(16));
                orderLines.add(orderLine);
            }
            order.setOrderLines(orderLines);
        } catch (SQLException e) {
            logger.error("Error: SQLException",e.getSQLState());
            logger.error("Error: SQLException",e.getErrorCode());
            throw new RuntimeException(e);
        }

        return order;
    }

    public List<Order> getOrdersForCustomer(long customerId) throws RuntimeException{
        List<Order> orders = new ArrayList<>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_FOR_CUST);){
            statement.setLong(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            long orderId = 0;
            Order order = null;
            while(resultSet.next()){
                long localOrderId = resultSet.getLong(4);
                if(orderId!=localOrderId){
                    order = new Order();
                    orders.add(order);
                    order.setId(localOrderId);
                    orderId = localOrderId;
                    order.setCustomerFirstName(resultSet.getString(1));
                    order.setCustomerLastLane(resultSet.getString(2));
                    order.setCustomerEmail(resultSet.getString(3));
                    order.setCreationDate(new Date(resultSet.getDate(5).getTime()));
                    order.setTotalDue(resultSet.getBigDecimal(6));
                    order.setStatus(resultSet.getString(7));
                    order.setSalespersonFirstName(resultSet.getString(8));
                    order.setSalespersonLastName(resultSet.getString(9));
                    order.setSalespersonEmail(resultSet.getString(10));
                    List<OrderLine> orderLines = new ArrayList<>();
                    order.setOrderLines(orderLines);
                }
                OrderLine orderLine = new OrderLine();
                orderLine.setQuantity(resultSet.getInt(11));
                orderLine.setProductCode(resultSet.getString(12));
                orderLine.setProductName(resultSet.getString(13));
                orderLine.setProductSize(resultSet.getInt(14));
                orderLine.setProductVariety(resultSet.getString(15));
                orderLine.setProductPrice(resultSet.getBigDecimal(16));
                order.getOrderLines().add(orderLine);
            }
        }catch(SQLException e){
            logger.error("Error: SQLException",e.getSQLState());
            logger.error("Error: SQLException",e.getErrorCode());
        }
        return orders;
    }
    @Override
    public List<Order> findAll() {return null;}

    @Override
    public Order update(Order dto) {return null;}

    @Override
    public Order create(Order dto) {return null;}

    @Override
    public void delete(long id) {}
}

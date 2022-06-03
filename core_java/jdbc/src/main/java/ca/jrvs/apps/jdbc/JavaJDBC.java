package ca.jrvs.apps.jdbc;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JavaJDBC{
    final Logger logger = LoggerFactory.getLogger (JavaJDBC.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        JavaJDBC jdbc = new JavaJDBC();
        DbConnectionManager dbConnectionManager = new DbConnectionManager(
                "localhost",
                "postgres",
                "postgres",
                "docker",
                "5432"
        );

        // connection instanciation try/catch block
        // SQLException are catched withing the method
        // Runtime Execptions are bubbled up here
        try {
            Connection connection = dbConnectionManager.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = customerDAO.findById(1000);
            System.out.println("Hello "+ customer);

            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1000);
            System.out.println(order);

            List<Order> orders = orderDAO.getOrdersForCustomer(789);
            orders.forEach(System.out::println);

        } catch (RuntimeException e) {
            dbConnectionManager.logger.error("RuntimeException:", e);
        } catch (Exception e) {
            dbConnectionManager.logger.error("Error: Unable to process:", e);
        }
    }
}

package ca.jrvs.apps.jdbc;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        try {
            Connection connection = dbConnectionManager.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = customerDAO.findById(1000);
            System.out.println("Hello "+customer.getFirstName());
        } catch (SQLException e) {
            dbConnectionManager.logger.error("SQLException:", e.toString());
        } catch (Exception ex) {
            dbConnectionManager.logger.error("Error: Unable to process:", ex.toString());
        }
    }
}

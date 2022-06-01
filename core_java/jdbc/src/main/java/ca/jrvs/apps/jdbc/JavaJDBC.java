package ca.jrvs.apps.jdbc;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class JavaJDBC{
    final Logger logger = LoggerFactory.getLogger (JavaJDBC.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        JavaJDBC jdbc = new JavaJDBC();
        DbConnectionManager dbConnectionManager = new DbConnectionManager(
                "localhost",
                "hplussport",
                "postgres",
                "password"
        );

        //connection instanciation try/catch block
        try {
            Connection connection = dbConnectionManager.getConnection();
        } catch (SQLException e) {
            dbConnectionManager.logger.error("SQLException:", e.toString());
        } catch (Exception ex) {
            dbConnectionManager.logger.error("Error: Unable to process:", ex.toString());
        }
    }
}

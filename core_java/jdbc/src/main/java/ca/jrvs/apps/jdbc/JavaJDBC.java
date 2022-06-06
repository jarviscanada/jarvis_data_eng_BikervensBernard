package ca.jrvs.apps.jdbc;

import org.apache.log4j.BasicConfigurator;

import java.sql.Connection;

public class JavaJDBC{

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
        } catch (RuntimeException e) {
            dbConnectionManager.logger.error("RuntimeException:", e);
        } catch (Exception e) {
            dbConnectionManager.logger.error("Error: Unable to process:", e);
        }
    }
}

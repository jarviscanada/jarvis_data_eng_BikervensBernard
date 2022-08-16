package ca.jrvs.apps.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionManager{
    private final String url;
    private final Properties properties;
    final Logger logger = LoggerFactory.getLogger (DbConnectionManager.class);

    public DbConnectionManager(String host, String database, String username, String password, String port) {
        this.url = "jdbc:postgresql://" + host + ":"+port +"/"+ database;
        this.properties = new Properties();
        this.properties.setProperty("user", username);
        this.properties.setProperty("password", password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.properties);
    }
}
package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.helper.MarketDataConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = {"ca.jrvs.apps.trading.dao","ca.jrvs.apps.trading.service"})
public class TestConfig {

    // Environment variable from .env file
    @Value("${token}")
    private String token;
    @Value("${jdbcUrl}")
    private String jdbcUrl;
    @Value("${PSQL_HOST}")
    private String PSQL_HOST;
    @Value("${PSQL_PORT}")
    private String PSQL_PORT;
    @Value("${PSQL_DB}")
    private String PSQL_DB;
    @Value("${PSQL_USER}")
    private String PSQL_USER;
    @Value("${PSQL_PASSWORD}")
    private String PSQL_PASSWORD;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        //jdbcUrl=jdbc:postgresql://
        //PSQL_HOST=localhost
        //PSQL_PORT=5432
        //PSQL_DB={X}
        //PSQL_USER=postgres
        //PSQL_PASSWORD={password}
        //
        //jdbc:postgresql://localhost:5432/{X}/{postgres}/{password}
        String url = jdbcUrl+PSQL_HOST+":"+PSQL_PORT+"/"+PSQL_DB;
        String user = PSQL_USER;
        String password = PSQL_PASSWORD;
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

    @Bean(name = "marketDataConfig")
    public MarketDataConfig marketDataConfig() {
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
        marketDataConfig.setToken(token);
        return marketDataConfig;
    }

    @Bean(name = "httpClientConnectionManager")
    public HttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(50);
        return connectionManager;
    }
}

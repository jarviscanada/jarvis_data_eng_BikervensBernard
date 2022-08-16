package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.controller.QuoteController;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.PositionEntityDao;
import ca.jrvs.apps.trading.dao.QuoteEntityDao;
import ca.jrvs.apps.trading.dao.SecurityOrderEntityDao;
import ca.jrvs.apps.trading.model.databaseEntity.AccountEntity;
import ca.jrvs.apps.trading.model.databaseEntity.TraderEntity;
import ca.jrvs.apps.trading.model.helper.MarketDataConfig;
import ca.jrvs.apps.trading.service.QuoteService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;


@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = {"ca.jrvs.apps.trading","ca.jrvs.apps.trading.dao","ca.jrvs.apps.trading.service"})
public class AppConfig {

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

    @Bean
    public DataSource dataSource() {
        String url = jdbcUrl+PSQL_HOST+":"+PSQL_PORT+"/"+"jrvstrading";
        String user = PSQL_USER;
        String password = PSQL_PASSWORD;
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

   @Bean
    public MarketDataConfig marketDataConfig() {
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
        marketDataConfig.setToken(token);
        return marketDataConfig;
    }

    @Bean
    public PoolingHttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(50);
        return connectionManager;
    }

    @Bean
    public PositionEntityDao positionEntityDao() {
        return new PositionEntityDao(dataSource());
    }

    @Bean
    public TraderEntity traderEntity() {
        return new TraderEntity();
    }

    @Bean
    public AccountEntity accountEntity() {
        return new AccountEntity();
    }

    @Bean
    public SecurityOrderEntityDao securityOrderEntityDao() {
        return new SecurityOrderEntityDao(dataSource());
    }

    @Bean
    public MarketDataDao marketDataDao() {
        return new MarketDataDao(httpClientConnectionManager(),marketDataConfig());
    }

    @Bean
    public QuoteService quoteService() {
        return new QuoteService(quoteDao() ,marketDataDao());
    }

    @Bean
    public QuoteEntityDao quoteDao() {
        return new QuoteEntityDao(dataSource(),httpClientConnectionManager(),marketDataConfig());
    }

    @Bean
    public QuoteController quoteController() {
        return new QuoteController(quoteService());
    }
}

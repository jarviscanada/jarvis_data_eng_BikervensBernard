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
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@org.springframework.context.annotation.Configuration
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
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(jdbcUrl+PSQL_HOST+":"+PSQL_PORT+"/"+PSQL_DB);
        basicDataSource.setUsername(PSQL_USER);
        basicDataSource.setPassword(PSQL_PASSWORD);
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
    public PoolingHttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(50);
        return connectionManager;
    }

    @Bean(name = "positionEntityDao")
    public PositionEntityDao positionEntityDao() {
        return new PositionEntityDao(dataSource());
    }

    @Bean(name = "traderEntity")
    public TraderEntity traderEntity() {
        return new TraderEntity();
    }
    @Bean(name = "accountEntity")
    public AccountEntity accountEntity() {
        return new AccountEntity();
    }

    @Bean(name = "securityOrderEntityDao")
    public SecurityOrderEntityDao securityOrderEntityDao() {
        return new SecurityOrderEntityDao(dataSource());
    }

    @Bean(name = "marketDataDao")
    public MarketDataDao marketDataDao() {
        return new MarketDataDao(httpClientConnectionManager(),marketDataConfig());
    }

    @Bean(name = "quoteService")
    public QuoteService quoteService() {
        return new QuoteService(quoteDao() ,marketDataDao());
    }

    @Bean(name = "quoteDao")
    public QuoteEntityDao quoteDao() {
        return new QuoteEntityDao(dataSource(),httpClientConnectionManager(),marketDataConfig());
    }

    @Bean(name = "quoteController")
    public QuoteController quoteController() {
        return new QuoteController(quoteService());
    }
}

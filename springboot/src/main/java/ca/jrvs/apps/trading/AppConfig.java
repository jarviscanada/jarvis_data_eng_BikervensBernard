package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.controller.QuoteController;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.helper.MarketDataConfig;
import ca.jrvs.apps.trading.service.QuoteService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@org.springframework.context.annotation.Configuration
public class AppConfig {

    @Value("${token}")
    private String apiKey;

    //@Bean
    public DataSource dataSource() {
        String jdbcUrl = "jdbc:postgresql://" +
                System.getenv("PSQL_HOST") + ":" +
                System.getenv("PSQL_PORT") +
                "/" +
                System.getenv("PSQL_DB");
        String user = System.getenv("PSQL_USER");
        String password = System.getenv("PSQL_PASSWORD");

        //Never log your credentials/secrets. Use IDE debugger instead
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(jdbcUrl);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

    @Bean(name = "marketDataConfig")
    public MarketDataConfig marketDataConfig() {
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
        marketDataConfig.setToken(apiKey);
        return marketDataConfig;
    }

    @Bean(name = "httpClientConnectionManager")
    public HttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(50);
        return connectionManager;
    }

    @Bean(name = "marketDataDao")
    public MarketDataDao marketDataDao() {
        return new MarketDataDao(httpClientConnectionManager(),marketDataConfig());
    }

    @Bean(name = "quoteService")
    public QuoteService quoteService() {
        return new QuoteService(marketDataDao());
    }

    @Bean(name = "quoteController")
    public QuoteController quoteController() {
        return new QuoteController(quoteService());
    }
}

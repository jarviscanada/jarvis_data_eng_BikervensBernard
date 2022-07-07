package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.Quote;
import ca.jrvs.apps.trading.model.helper.MarketDataConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

    @Autowired
    private PoolingHttpClientConnectionManager cm;
    @Autowired
    private MarketDataConfig marketDataConfig;
    @Autowired
    private QuoteDao quoteDao;
    private Quote savedQuote;
    private static final String API = "https://cloud.iexapis.com/";
    private static final String END_POINT = "v1/";
    @Value("${token}")
    private String token;
    @Value("${jdbcUrl}")
    private String URL;
    @Value("${PSQL_DB}")
    private String DB;
    @Value("${PSQL_USER}")
    private String PSQL_USER;
    @Value("${PSQL_PASSWORD}")
    private String PSQL_PASSWORD;

    @Before
    public void init() {
        /*http
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost(API+END_POINT);
        marketDataConfig.setToken(token);
*/
        //jdbc
        String url = URL+"localhost:5432/"+DB;
        String user = PSQL_USER;
        String password = PSQL_PASSWORD;
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);

        this.quoteDao = new QuoteDao(basicDataSource, cm, marketDataConfig);
    }

    @Test
    public void insertOne() {
        savedQuote = new Quote();
        savedQuote.setPreviousClose(10.f);
        savedQuote.setIexAskPrice(10.2f);
        savedQuote.setIexAskSize(10);
        savedQuote.setIexBidPrice(14.1f);
        savedQuote.setIexBidSize(10);
        savedQuote.setId("AAPL");
        savedQuote.setSymbol("AAPL");
        quoteDao.save(savedQuote);
    }
}
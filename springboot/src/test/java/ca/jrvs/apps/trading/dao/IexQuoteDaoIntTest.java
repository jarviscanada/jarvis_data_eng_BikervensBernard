package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.QuoteEntity;
import ca.jrvs.apps.trading.model.helper.MarketDataConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class IexQuoteDaoIntTest {

    @Autowired
    private PoolingHttpClientConnectionManager cm;
    @Autowired
    private MarketDataConfig marketDataConfig;
    @Autowired
    private QuoteDao quoteDao;

    private QuoteEntity savedIexQuote;

    @Value("${token}")
    private String token;
    @Value("${jdbcUrl}")
    private String URL;
    @Value("${PSQL_DB_TEST}")
    private String DB;
    @Value("${PSQL_USER}")
    private String PSQL_USER;
    @Value("${PSQL_PASSWORD}")
    private String PSQL_PASSWORD;

    @Before
    public void setUp() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(URL+"localhost:5432/"+DB);
        basicDataSource.setUsername(PSQL_USER);
        basicDataSource.setPassword(PSQL_PASSWORD);
        this.quoteDao = new QuoteDao(basicDataSource, cm, marketDataConfig);
        this.savedIexQuote = new QuoteEntity();
        if(!this.quoteDao.existsById("AAPL")) {
            this.savedIexQuote = new QuoteEntity();
            this.savedIexQuote.setLastPrice(10.d);
            this.savedIexQuote.setAskPrice(10.2d);
            this.savedIexQuote.setAskSize(10);
            this.savedIexQuote.setBidPrice(14.1d);
            this.savedIexQuote.setBidSize(10);
            this.savedIexQuote.setTicker("AAPL");
            this.savedIexQuote.setId("AAPL");
            this.quoteDao.save(savedIexQuote);
        }
    }

    @After
    public void tearDown() {
        savedIexQuote = new QuoteEntity();
        if(this.quoteDao.existsById("AAPL")) {
            this.quoteDao.deleteById("AAPL");
        }
    }

    // (C)RUD
    @Test
    public void insertOne() {
        assertTrue(this.quoteDao.save(savedIexQuote)!=null);
    }

    // C(R)UD
    @Test
    public void existsById() {
        assertTrue(this.quoteDao.existsById("AAPL"));
    }


    // C(R)UD
    @Test
    public void findAll() {
        Iterable<QuoteEntity> e = this.quoteDao.findAll();
        e.forEach(q->assertTrue(this.quoteDao.existsById(q.getId())));
    }

    @Test
    public void findAllById() {
        savedIexQuote = new QuoteEntity();
        Iterable<QuoteEntity> e = this.quoteDao.findAllById(Arrays.asList("AAPL"));
        e.forEach(q-> {
            assertTrue(this.quoteDao.existsById(q.getId()));
            assertTrue(q.getId().equals("AAPL"));
        });
    }

    // CR(U)D
    @Test
    public void updateOne() {
        this.savedIexQuote.setLastPrice(1000d);
        QuoteEntity e = this.quoteDao.save(this.savedIexQuote);
        assertTrue(e.getLastPrice() == 1000d);
    }

    // CRU(D)
    @Test
    public void deleteById() {
        try {
            this.quoteDao.deleteById("AAPL");
        } catch (IncorrectResultSizeDataAccessException e) {
            assertTrue(false);
        }
    }


}
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
//@Sql({"classpath:schema.sql"})
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
    @Value("${PSQL_DB}")
    private String DB;
    @Value("${PSQL_USER}")
    private String PSQL_USER;
    @Value("${PSQL_PASSWORD}")
    private String PSQL_PASSWORD;

    @Before
    public void setUp() {
        String url = URL+"localhost:5432/"+DB;
        String user = PSQL_USER;
        String password = PSQL_PASSWORD;
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        this.quoteDao = new QuoteDao(basicDataSource, cm, marketDataConfig);
        savedIexQuote = new QuoteEntity();
        if(!this.quoteDao.existsById("AAPL")) {
            savedIexQuote = new QuoteEntity();
            savedIexQuote.setLastPrice(10.d);
            savedIexQuote.setAskPrice(10.2d);
            savedIexQuote.setAskSize(10);
            savedIexQuote.setBidPrice(14.1d);
            savedIexQuote.setBidSize(10);
            savedIexQuote.setTicker("AAPL");
            savedIexQuote.setId("AAPL");
            quoteDao.save(savedIexQuote);
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
        assertTrue(quoteDao.save(savedIexQuote)!=null);
    }

    // C(R)UD
    @Test
    public void existsById() {
        assertTrue(quoteDao.existsById("AAPL"));
    }


    // C(R)UD
    @Test
    public void findAll() {
        Iterable<QuoteEntity> e = quoteDao.findAll();
        e.forEach(q->assertTrue(quoteDao.existsById(q.getId())));
    }

    @Test
    public void findAllById() {
        savedIexQuote = new QuoteEntity();
        Iterable<QuoteEntity> e = quoteDao.findAllById(Arrays.asList("AAPL"));
        e.forEach(q-> {
            assertTrue(quoteDao.existsById(q.getId()));
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
            quoteDao.deleteById("AAPL");
        } catch (IncorrectResultSizeDataAccessException e) {
            assertTrue(false);
        }
    }


}
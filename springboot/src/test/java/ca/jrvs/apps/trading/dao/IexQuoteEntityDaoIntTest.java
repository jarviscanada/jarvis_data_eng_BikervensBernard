package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.databaseEntity.QuoteEntity;
import ca.jrvs.apps.trading.model.helper.MarketDataConfig;
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
public class IexQuoteEntityDaoIntTest {

    @Autowired
    private PoolingHttpClientConnectionManager cm;
    @Autowired
    private MarketDataConfig marketDataConfig;
    @Autowired
    private QuoteEntityDao quoteEntityDao;

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
        this.savedIexQuote = new QuoteEntity();
        if(!this.quoteEntityDao.existsById("AAPL")) {
            this.savedIexQuote = new QuoteEntity();
            this.savedIexQuote.setLastPrice(10.d);
            this.savedIexQuote.setAskPrice(10.2d);
            this.savedIexQuote.setAskSize(10);
            this.savedIexQuote.setBidPrice(14.1d);
            this.savedIexQuote.setBidSize(10);
            this.savedIexQuote.setTicker("AAPL");
            this.savedIexQuote.setId("AAPL");
            this.quoteEntityDao.save(savedIexQuote);
        }
    }

    @After
    public void tearDown() {
        savedIexQuote = new QuoteEntity();
        if(this.quoteEntityDao.existsById("AAPL")) {
            this.quoteEntityDao.deleteById("AAPL");
        }
    }

    // (C)RUD
    @Test
    public void insertOne() {
        assertTrue(this.quoteEntityDao.save(savedIexQuote)!=null);
    }

    // C(R)UD
    @Test
    public void existsById() {
        assertTrue(this.quoteEntityDao.existsById("AAPL"));
    }


    // C(R)UD
    @Test
    public void findAll() {
        Iterable<QuoteEntity> e = this.quoteEntityDao.findAll();
        e.forEach(q->assertTrue(this.quoteEntityDao.existsById(q.getId())));
    }

    @Test
    public void findAllById() {
        savedIexQuote = new QuoteEntity();
        Iterable<QuoteEntity> e = this.quoteEntityDao.findAllById(Arrays.asList("AAPL"));
        e.forEach(q-> {
            assertTrue(this.quoteEntityDao.existsById(q.getId()));
            assertTrue(q.getId().equals("AAPL"));
        });
    }

    // CR(U)D
    @Test
    public void updateOne() {
        this.savedIexQuote.setLastPrice(1000d);
        QuoteEntity e = this.quoteEntityDao.save(this.savedIexQuote);
        assertTrue(e.getLastPrice() == 1000d);
    }

    // CRU(D)
    @Test
    public void deleteById() {
        try {
            this.quoteEntityDao.deleteById("AAPL");
        } catch (IncorrectResultSizeDataAccessException e) {
            assertTrue(false);
        }
    }
}
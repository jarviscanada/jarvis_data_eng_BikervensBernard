package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.IexQuote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

    @Autowired
    private QuoteService quoteService;
    @Autowired
    private QuoteDao quoteDao;

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
    public void setUp() throws Exception {
        this.quoteDao.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findIexQuoteByTicker() {
        IexQuote quote = null;
        try {
            quote = this.quoteService.findIexQuoteByTicker("AAPL");
        }catch (IllegalArgumentException e) {
            assertTrue(false);
        }
        assertTrue(quote!=null);
        assertEquals("AAPL",quote.getSymbol());
    }

    @Test
    public void updateMarketData() {
    }

    @Test
    public void buildAndSaveQuoteDbEntityFromIexQuoteApi() {
    }

    @Test
    public void saveQuotes() {
    }

    @Test
    public void saveQuote() {
    }

    @Test
    public void findAllQuotes() {
    }
}
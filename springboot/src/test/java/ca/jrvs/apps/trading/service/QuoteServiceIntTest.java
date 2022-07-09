package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteEntityDao;
import ca.jrvs.apps.trading.model.IexQuote;
import ca.jrvs.apps.trading.model.QuoteEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

    @Autowired
    private QuoteService quoteService;
    @Autowired
    private QuoteEntityDao quoteEntityDao;

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
    public void setUp() throws Exception {
        this.quoteEntityDao.deleteAll();
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
        if(!this.quoteEntityDao.existsById("AAPL")) {
            QuoteEntity savedIexQuote = new QuoteEntity();
            savedIexQuote.setLastPrice(10.d);
            savedIexQuote.setAskPrice(10.2d);
            savedIexQuote.setAskSize(10);
            savedIexQuote.setBidPrice(14.1d);
            savedIexQuote.setBidSize(10);
            savedIexQuote.setTicker("AAPL");
            savedIexQuote.setId("AAPL");
            this.quoteEntityDao.save(savedIexQuote);
        }
        Optional<QuoteEntity> fromDatabase = this.quoteEntityDao.findById("AAPL");
        assertTrue(fromDatabase.isPresent());
        fromDatabase.get().setLastPrice(10.d);
        fromDatabase.get().setAskPrice(10.2d);
        fromDatabase.get().setAskSize(10);
        fromDatabase.get().setBidPrice(14.1d);
        fromDatabase.get().setBidSize(10);
        fromDatabase.get().setTicker("AAPL");
        fromDatabase.get().setId("AAPL");
        assertTrue(fromDatabase.get().getAskPrice().equals(10.2d));
        this.quoteService.updateMarketData();
        fromDatabase = this.quoteEntityDao.findById("AAPL");
        assertTrue(fromDatabase.isPresent());
        assertFalse(fromDatabase.get().getAskPrice().equals(10.2d));
    }

    @Test
    public void buildAndSaveQuoteDbEntityFromIexQuoteApi() {
        IexQuote quoteFromAPI = this.quoteService.findIexQuoteByTicker("AAPL");
        assertTrue(quoteFromAPI!=null);
        assertTrue(quoteFromAPI.getSymbol().equals("AAPL"));
        this.quoteService.buildAndSaveQuoteDbEntityFromIexQuote(quoteFromAPI);

        Optional<QuoteEntity> quoteFromdb = this.quoteEntityDao.findById("AAPL");
        assertTrue(quoteFromdb.isPresent());
        assertTrue(quoteFromdb.get().getLastPrice().equals(quoteFromAPI.getLatestPrice()));
    }

    @Test
    public void saveQuotes() {

        try {
            IexQuote quoteFromAPI1 = this.quoteService.findIexQuoteByTicker("SNAP");
            assertTrue(quoteFromAPI1!=null);
            assertTrue(quoteFromAPI1.getSymbol().equals("SNAP"));
            this.quoteService.buildAndSaveQuoteDbEntityFromIexQuote(quoteFromAPI1);

            IexQuote quoteFromAPI2 = this.quoteService.findIexQuoteByTicker("FB");
            assertTrue(quoteFromAPI2!=null);
            assertTrue(quoteFromAPI2.getSymbol().equals("FB"));
            this.quoteService.buildAndSaveQuoteDbEntityFromIexQuote(quoteFromAPI2);

            this.quoteService.saveQuotes(Arrays.asList("FB","SNAP"));

            this.quoteEntityDao.findAllById(Arrays.asList("FB","SNAP"));
            Stream<QuoteEntity> x =  this.quoteService.findAllQuotes().stream().filter(quoteEntity -> quoteEntity.getId().equals("FB") || quoteEntity.getId().equals("SNAP"));
            assertEquals(2, x.collect(Collectors.toList()).size());
        }
        catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void saveQuote() {
        QuoteEntity savedIexQuote = new QuoteEntity();
        savedIexQuote.setLastPrice(10.d);
        savedIexQuote.setAskPrice(10.2d);
        savedIexQuote.setAskSize(10);
        savedIexQuote.setBidPrice(14.1d);
        savedIexQuote.setBidSize(10);
        savedIexQuote.setTicker("AAPL");
        savedIexQuote.setId("AAPL");
        this.quoteService.saveQuote(savedIexQuote);
        Optional<QuoteEntity> found = this.quoteEntityDao.findById("AAPL");
        assertTrue(found.isPresent());
        assertTrue(found.get().getLastPrice().equals(10.d));
        assertTrue(found.get().getAskPrice().equals(10.2d));
        assertTrue(found.get().getAskSize().equals(10));
        assertTrue(found.get().getBidPrice().equals(14.1d));
        assertTrue(found.get().getBidSize().equals(10));
        assertTrue(found.get().getTicker().equals("AAPL"));
        assertTrue(found.get().getId().equals("AAPL"));
    }

    @Test
    public void findAllQuotes() {
        List<QuoteEntity> all = this.quoteService.findAllQuotes();
        assertTrue(all.size() == 0);

        IexQuote quoteFromAPI1 = this.quoteService.findIexQuoteByTicker("SNAP");
        this.quoteService.buildAndSaveQuoteDbEntityFromIexQuote(quoteFromAPI1);

        IexQuote quoteFromAPI2 = this.quoteService.findIexQuoteByTicker("FB");
        this.quoteService.buildAndSaveQuoteDbEntityFromIexQuote(quoteFromAPI2);

        IexQuote quoteFromAPI3 = this.quoteService.findIexQuoteByTicker("COUP");
        this.quoteService.buildAndSaveQuoteDbEntityFromIexQuote(quoteFromAPI3);


        IexQuote quoteFromAPI4 = this.quoteService.findIexQuoteByTicker("SQ");
        this.quoteService.buildAndSaveQuoteDbEntityFromIexQuote(quoteFromAPI4);

        this.quoteService.findAllQuotes().forEach(quoteEntity -> {
            assertTrue(quoteEntity.getId().equals("SNAP") ||
                    quoteEntity.getId().equals("FB") ||
                    quoteEntity.getId().equals("COUP") ||
                    quoteEntity.getId().equals("SQ")
            );
        });

        assertTrue(this.quoteService.findAllQuotes().size() == 4);

    }
}
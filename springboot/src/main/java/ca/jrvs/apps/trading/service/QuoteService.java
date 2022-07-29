package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteEntityDao;
import ca.jrvs.apps.trading.model.IexQuote;
import ca.jrvs.apps.trading.model.databaseEntity.QuoteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.transaction.annotation.Transactional()
@org.springframework.stereotype.Service
public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
    private final QuoteEntityDao quoteEntityDao;
    private final MarketDataDao marketDataDao;

    @Autowired
    public QuoteService(QuoteEntityDao quoteEntityDao, MarketDataDao marketDataDao) {
        this.quoteEntityDao = quoteEntityDao;
        this.marketDataDao = marketDataDao;
    }

    /***/
    public IexQuote findIexQuoteByTicker(String ticker) {
        return this.marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker+ " is invalid"));
    }


    public void updateMarketData() {
        List<QuoteEntity> all = this.findAllQuotes();
        for (int i = 0 ; i < all.size(); i++) {
            QuoteEntity currentEntityData = all.get(i);
            String id = currentEntityData.getId();
            IexQuote updatedData = findIexQuoteByTicker(id);

            //update against iex database
            currentEntityData.setTicker(updatedData.getSymbol());
            currentEntityData.setId(updatedData.getSymbol());

            currentEntityData.setLastPrice(updatedData.getLatestPrice() == null ? 0 : updatedData.getLatestPrice());
            currentEntityData.setAskPrice(updatedData.getIexAskPrice() == null ? 0 : Double.valueOf(updatedData.getIexAskPrice()));
            currentEntityData.setAskSize(updatedData.getIexAskSize() == null ? 0 : updatedData.getIexAskSize());
            currentEntityData.setBidPrice(updatedData.getIexBidPrice() == null ? 0 : Double.valueOf(updatedData.getIexBidPrice()));
            currentEntityData.setBidSize(updatedData.getIexBidSize() == null ? 0 : updatedData.getIexBidSize());
            currentEntityData.setIexQuote(updatedData);

            this.quoteEntityDao.save(currentEntityData);
        }
    }

    public QuoteEntity buildAndSaveQuoteDbEntityFromIexQuote(IexQuote updatedData) {
        QuoteEntity currentEntityData = new QuoteEntity();

        //update against iex database
        currentEntityData.setLastPrice(updatedData.getLatestPrice() == null ? 0 :updatedData.getLatestPrice());
        currentEntityData.setTicker(updatedData.getSymbol());
        currentEntityData.setId(updatedData.getSymbol());
        currentEntityData.setAskPrice(updatedData.getIexAskPrice() == null ? 0 : Double.valueOf(updatedData.getIexAskPrice()) );
        currentEntityData.setAskSize(updatedData.getIexAskSize() == null ? 0 : updatedData.getIexAskSize());
        currentEntityData.setBidPrice(updatedData.getIexBidPrice() == null ? 0 : Double.valueOf(updatedData.getIexBidPrice()) );
        currentEntityData.setBidSize(updatedData.getIexBidSize()== null ? 0 :updatedData.getIexBidSize());
        currentEntityData.setIexQuote(updatedData);

        return this.quoteEntityDao.save(currentEntityData);
    }

    public List<QuoteEntity> saveQuotes(List<String> tickers) {
        List<QuoteEntity> out = new ArrayList<>();
        tickers.forEach(t->{
            try {
                IexQuote updatedData = findIexQuoteByTicker(t);
                out.add(buildAndSaveQuoteDbEntityFromIexQuote(updatedData));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(t+ " is invalid");
            }
        });
        return out;
    }

    public QuoteEntity saveQuote(QuoteEntity quoteEntity) {
        return this.quoteEntityDao.save(quoteEntity);
    }

    public List<QuoteEntity> findAllQuotes() {
        return (List<QuoteEntity>) this.quoteEntityDao.findAll();
    }

    public List<QuoteEntity> deleteQuotes(String[] quotes) {
        for (String q: quotes) {
            this.quoteEntityDao.deleteById(q);
        }
        return this.findAllQuotes();
    }
}

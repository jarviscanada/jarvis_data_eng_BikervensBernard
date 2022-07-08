package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.IexQuote;
import ca.jrvs.apps.trading.model.QuoteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.transaction.annotation.Transactional()
@org.springframework.stereotype.Service
public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
    private final QuoteDao quoteDao;
    private final MarketDataDao marketDataDao;

    @Autowired
    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    /***/
    public IexQuote findIexQuoteByTicker(String ticker) {
        return this.marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker+ " is invalid"));
    }


    public void updateMarketData() {
        List<QuoteEntity> all = (List<QuoteEntity>) this.quoteDao.findAll();
        for (int i = 0 ; i < all.size(); i++) {
            QuoteEntity currentEntityData = all.get(i);
            String id = currentEntityData.getId();
            IexQuote updatedData = findIexQuoteByTicker(id);

            //update against iex database
            currentEntityData.setLastPrice(updatedData.getLatestPrice());
            currentEntityData.setTicker(updatedData.getSymbol());
            currentEntityData.setId(updatedData.getSymbol());
            currentEntityData.setAskPrice(Double.valueOf(updatedData.getIexAskPrice()));
            currentEntityData.setAskSize(updatedData.getIexAskSize());
            currentEntityData.setBidPrice(Double.valueOf(updatedData.getIexBidPrice()));
            currentEntityData.setBidSize(updatedData.getIexBidSize());
            currentEntityData.setIexQuote(updatedData);

            this.quoteDao.save(currentEntityData);
        }
    }

    public QuoteEntity buildAndSaveQuoteDbEntityFromIexQuoteApi(IexQuote updatedData) {
        QuoteEntity currentEntityData = new QuoteEntity();

        //update against iex database
        currentEntityData.setLastPrice(updatedData.getLatestPrice());
        currentEntityData.setTicker(updatedData.getSymbol());
        currentEntityData.setId(updatedData.getSymbol());
        currentEntityData.setAskPrice(Double.valueOf(updatedData.getIexAskPrice()));
        currentEntityData.setAskSize(updatedData.getIexAskSize());
        currentEntityData.setBidPrice(Double.valueOf(updatedData.getIexBidPrice()));
        currentEntityData.setBidSize(updatedData.getIexBidSize());
        currentEntityData.setIexQuote(updatedData);

        return this.quoteDao.save(currentEntityData);
    }

    public List<QuoteEntity> saveQuotes(List<String> tickers) {
        List<QuoteEntity> out = new ArrayList<>();
        tickers.forEach(t->{
            try {
                IexQuote updatedData = findIexQuoteByTicker(t);
                out.add(buildAndSaveQuoteDbEntityFromIexQuoteApi(updatedData));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(t+ " is invalid");
            }
        });
        return out;
    }

    public QuoteEntity saveQuote(QuoteEntity quoteEntity) {
        return this.quoteDao.save(quoteEntity);
    }

    public List<QuoteEntity> findAllQuotes(String ticker) {
        return (List<QuoteEntity>) this.quoteDao.findAll();
    }
}

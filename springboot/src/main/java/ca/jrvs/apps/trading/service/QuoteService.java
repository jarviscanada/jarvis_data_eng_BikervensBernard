package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.IexQuoteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.transaction.annotation.Transactional()
@org.springframework.stereotype.Service
public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
    //private QuoteDao quoteDao;
    //@Autowired
    private MarketDataDao marketDataDao;

    @Autowired
    public QuoteService(/*QuoteDao quoteDao, */MarketDataDao marketDataDao) {
        //this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    /***/
    public IexQuoteModel findIexQuoteByTicker(String ticker) {
        return this.marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker+ " is invalid"));
    }
}

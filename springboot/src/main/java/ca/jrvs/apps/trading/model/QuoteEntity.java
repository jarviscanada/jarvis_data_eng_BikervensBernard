package ca.jrvs.apps.trading.model;

@org.springframework.stereotype.Component
public class QuoteEntity implements Entity<String>{
    private String ticker;
    private String id;
    private Double lastPrice;
    private Double bidPrice;
    private Integer bidSize;
    private Double askPrice;
    private Integer askSize;

    public IexQuote getIexQuote() {
        return iexQuote;
    }

    public void setIexQuote(IexQuote iexQuote) {
        this.iexQuote = iexQuote;
    }

    private IexQuote iexQuote;

    public Integer getAskSize() {
        return askSize;
    }

    public void setAskSize(Integer askSize) {
        this.askSize = askSize;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker.toUpperCase();
        this.id = ticker.toUpperCase();
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Integer getBidSize() {
        return bidSize;
    }

    public void setBidSize(Integer bidSize) {
        this.bidSize = bidSize;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    @Override
    public String getId() {
        return this.ticker.toUpperCase();
    }

    @Override
    public void setId(String s) {
        this.ticker = s.toUpperCase();
        this.id = s.toUpperCase();
    }

    public QuoteEntity() {}
    public QuoteEntity(String ticker, String id, Double lastPrice, Double bidPrice, Integer bidSize, Double askPrice) {
        this.ticker = ticker;
        this.id = id;
        this.lastPrice = lastPrice;
        this.bidPrice = bidPrice;
        this.bidSize = bidSize;
        this.askPrice = askPrice;
    }

    @Override
    public String toString() {
        return "QuoteEntity{" +
                "ticker='" + ticker + '\'' +
                ", id='" + id + '\'' +
                ", lastPrice=" + lastPrice +
                ", bidPrice=" + bidPrice +
                ", bidSize=" + bidSize +
                ", askPrice=" + askPrice +
                '}';
    }
}

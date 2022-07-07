package ca.jrvs.apps.trading.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "avgTotalVolume",
        "calculationPrice",
        "change",
        "changePercent",
        "close",
        "closeSource",
        "closeTime",
        "companyName",
        "currency",
        "delayedPrice",
        "delayedPriceTime",
        "extendedChange",
        "extendedChangePercent",
        "extendedPrice",
        "extendedPriceTime",
        "high",
        "highSource",
        "highTime",
        "iexAskPrice",
        "iexAskSize",
        "iexBidPrice",
        "iexBidSize",
        "iexClose",
        "iexCloseTime",
        "iexLastUpdated",
        "iexMarketPercent",
        "iexOpen",
        "iexOpenTime",
        "iexRealtimePrice",
        "iexRealtimeSize",
        "iexVolume",
        "lastTradeTime",
        "latestPrice",
        "latestSource",
        "latestTime",
        "latestUpdate",
        "latestVolume",
        "low",
        "lowSource",
        "lowTime",
        "marketCap",
        "oddLotDelayedPrice",
        "oddLotDelayedPriceTime",
        "open",
        "openTime",
        "openSource",
        "peRatio",
        "previousClose",
        "previousVolume",
        "primaryExchange",
        "symbol",
        "volume",
        "week52High",
        "week52Low",
        "ytdChange",
        "isUSMarketOpen"
})
@Generated("jsonschema2pojo")
public class Quote implements Serializable, Entity {

    private String ticker;
    private Double lastPrice;
    private Double bidPrice;
    private Integer bidSize;
    private Double askPrice;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
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

    public Integer getAskSize() {
        return askSize;
    }

    public void setAskSize(Integer askSize) {
        this.askSize = askSize;
    }

    public void setIexBidPrice(Float iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    private Integer askSize;



    @JsonProperty("avgTotalVolume")
    private Double avgTotalVolume;
    @JsonProperty("calculationPrice")
    private String calculationPrice;
    @JsonProperty("change")
    private Double change;
    @JsonProperty("changePercent")
    private Double changePercent;
    @JsonProperty("close")
    private Double close;
    @JsonProperty("closeSource")
    private String closeSource;
    @JsonProperty("closeTime")
    private Double closeTime;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("delayedPrice")
    private Double delayedPrice;
    @JsonProperty("delayedPriceTime")
    private Double delayedPriceTime;
    @JsonProperty("extendedChange")
    private Double extendedChange;
    @JsonProperty("extendedChangePercent")
    private Double extendedChangePercent;
    @JsonProperty("extendedPrice")
    private Double extendedPrice;
    @JsonProperty("extendedPriceTime")
    private Double extendedPriceTime;
    @JsonProperty("high")
    private Double high;
    @JsonProperty("highSource")
    private String highSource;
    @JsonProperty("highTime")
    private Double highTime;
    @JsonProperty("iexAskPrice")
    private Float iexAskPrice;
    @JsonProperty("iexAskSize")
    private Integer iexAskSize;
    @JsonProperty("iexBidPrice")
    private Float iexBidPrice;
    @JsonProperty("iexBidSize")
    private Integer iexBidSize;
    @JsonProperty("iexClose")
    private Double iexClose;
    @JsonProperty("iexCloseTime")
    private Double iexCloseTime;
    @JsonProperty("iexLastUpdated")
    private Double iexLastUpdated;
    @JsonProperty("iexMarketPercent")
    private Double iexMarketPercent;
    @JsonProperty("iexOpen")
    private Double iexOpen;
    @JsonProperty("iexOpenTime")
    private Double iexOpenTime;
    @JsonProperty("iexRealtimePrice")
    private Double iexRealtimePrice;
    @JsonProperty("iexRealtimeSize")
    private Integer iexRealtimeSize;
    @JsonProperty("iexVolume")
    private Double iexVolume;
    @JsonProperty("lastTradeTime")
    private Double lastTradeTime;
    @JsonProperty("latestPrice")
    private Double latestPrice;
    @JsonProperty("latestSource")
    private String latestSource;
    @JsonProperty("latestTime")
    private String latestTime;
    @JsonProperty("latestUpdate")
    private Double latestUpdate;
    @JsonProperty("latestVolume")
    private Double latestVolume;
    @JsonProperty("low")
    private Double low;
    @JsonProperty("lowSource")
    private String lowSource;
    @JsonProperty("lowTime")
    private Double lowTime;
    @JsonProperty("marketCap")
    private Double marketCap;
    @JsonProperty("oddLotDelayedPrice")
    private Double oddLotDelayedPrice;
    @JsonProperty("oddLotDelayedPriceTime")
    private Double oddLotDelayedPriceTime;
    @JsonProperty("open")
    private Double open;
    @JsonProperty("openTime")
    private Double openTime;
    @JsonProperty("openSource")
    private String openSource;
    @JsonProperty("peRatio")
    private Double peRatio;
    @JsonProperty("previousClose")
    private Float previousClose;
    @JsonProperty("previousVolume")
    private Double previousVolume;
    @JsonProperty("primaryExchange")
    private String primaryExchange;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("volume")
    private Double volume;
    @JsonProperty("week52High")
    private Double week52High;
    @JsonProperty("week52Low")
    private Double week52Low;
    @JsonProperty("ytdChange")
    private Double ytdChange;
    @JsonProperty("isUSMarketOpen")
    private Boolean isUSMarketOpen;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static Long serialVersionUID = -1246767282650210470L;

    public Quote() {}
    public Quote(String ticker, float last_price, float bid_price, int bid_size, float ask_price, int ask_size) {
        this.setId(ticker);
        this.setPreviousClose(last_price);
        this.setIexBidPrice(bid_price);
        this.setIexBidSize(bid_size);
        this.setIexAskPrice(ask_price);
        this.setIexAskSize(ask_size);
    }

    @JsonProperty("avgTotalVolume")
    public Double getAvgTotalVolume() {
        return avgTotalVolume;
    }

    @JsonProperty("avgTotalVolume")
    public void setAvgTotalVolume(Double avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    @JsonProperty("calculationPrice")
    public String getCalculationPrice() {
        return calculationPrice;
    }

    @JsonProperty("calculationPrice")
    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    @JsonProperty("change")
    public Double getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(Double change) {
        this.change = change;
    }

    @JsonProperty("changePercent")
    public Double getChangePercent() {
        return changePercent;
    }

    @JsonProperty("changePercent")
    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    @JsonProperty("close")
    public Double getClose() {
        return close;
    }

    @JsonProperty("close")
    public void setClose(Double close) {
        this.close = close;
    }

    @JsonProperty("closeSource")
    public String getCloseSource() {
        return closeSource;
    }

    @JsonProperty("closeSource")
    public void setCloseSource(String closeSource) {
        this.closeSource = closeSource;
    }

    @JsonProperty("closeTime")
    public Double getCloseTime() {
        return closeTime;
    }

    @JsonProperty("closeTime")
    public void setCloseTime(Double closeTime) {
        this.closeTime = closeTime;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("delayedPrice")
    public Double getDelayedPrice() {
        return delayedPrice;
    }

    @JsonProperty("delayedPrice")
    public void setDelayedPrice(Double delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    @JsonProperty("delayedPriceTime")
    public Double getDelayedPriceTime() {
        return delayedPriceTime;
    }

    @JsonProperty("delayedPriceTime")
    public void setDelayedPriceTime(Double delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    @JsonProperty("extendedChange")
    public Double getExtendedChange() {
        return extendedChange;
    }

    @JsonProperty("extendedChange")
    public void setExtendedChange(Double extendedChange) {
        this.extendedChange = extendedChange;
    }

    @JsonProperty("extendedChangePercent")
    public Double getExtendedChangePercent() {
        return extendedChangePercent;
    }

    @JsonProperty("extendedChangePercent")
    public void setExtendedChangePercent(Double extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    @JsonProperty("extendedPrice")
    public Double getExtendedPrice() {
        return extendedPrice;
    }

    @JsonProperty("extendedPrice")
    public void setExtendedPrice(Double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    @JsonProperty("extendedPriceTime")
    public Double getExtendedPriceTime() {
        return extendedPriceTime;
    }

    @JsonProperty("extendedPriceTime")
    public void setExtendedPriceTime(Double extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    @JsonProperty("high")
    public Double getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(Double high) {
        this.high = high;
    }

    @JsonProperty("highSource")
    public String getHighSource() {
        return highSource;
    }

    @JsonProperty("highSource")
    public void setHighSource(String highSource) {
        this.highSource = highSource;
    }

    @JsonProperty("highTime")
    public Double getHighTime() {
        return highTime;
    }

    @JsonProperty("highTime")
    public void setHighTime(Double highTime) {
        this.highTime = highTime;
    }

    @JsonProperty("iexAskPrice")
    public Float getIexAskPrice() {
        return iexAskPrice;
    }

    @JsonProperty("iexAskPrice")
    public void setIexAskPrice(Float iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    @JsonProperty("iexAskSize")
    public Integer getIexAskSize() {
        return iexAskSize;
    }

    @JsonProperty("iexAskSize")
    public void setIexAskSize(Integer iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    @JsonProperty("iexBidPrice")
    public Float getIexBidPrice() {
        return iexBidPrice;
    }

    @JsonProperty("iexBidPrice")
    public void setIexBidPrice(float iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    @JsonProperty("iexBidSize")
    public Integer getIexBidSize() {
        return iexBidSize;
    }

    @JsonProperty("iexBidSize")
    public void setIexBidSize(Integer iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    @JsonProperty("iexClose")
    public Double getIexClose() {
        return iexClose;
    }

    @JsonProperty("iexClose")
    public void setIexClose(Double iexClose) {
        this.iexClose = iexClose;
    }

    @JsonProperty("iexCloseTime")
    public Double getIexCloseTime() {
        return iexCloseTime;
    }

    @JsonProperty("iexCloseTime")
    public void setIexCloseTime(Double iexCloseTime) {
        this.iexCloseTime = iexCloseTime;
    }

    @JsonProperty("iexLastUpdated")
    public Double getIexLastUpdated() {
        return iexLastUpdated;
    }

    @JsonProperty("iexLastUpdated")
    public void setIexLastUpdated(Double iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    @JsonProperty("iexMarketPercent")
    public Double getIexMarketPercent() {
        return iexMarketPercent;
    }

    @JsonProperty("iexMarketPercent")
    public void setIexMarketPercent(Double iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    @JsonProperty("iexOpen")
    public Double getIexOpen() {
        return iexOpen;
    }

    @JsonProperty("iexOpen")
    public void setIexOpen(Double iexOpen) {
        this.iexOpen = iexOpen;
    }

    @JsonProperty("iexOpenTime")
    public Double getIexOpenTime() {
        return iexOpenTime;
    }

    @JsonProperty("iexOpenTime")
    public void setIexOpenTime(Double iexOpenTime) {
        this.iexOpenTime = iexOpenTime;
    }

    @JsonProperty("iexRealtimePrice")
    public Double getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    @JsonProperty("iexRealtimePrice")
    public void setIexRealtimePrice(Double iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    @JsonProperty("iexRealtimeSize")
    public Integer getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    @JsonProperty("iexRealtimeSize")
    public void setIexRealtimeSize(Integer iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    @JsonProperty("iexVolume")
    public Double getIexVolume() {
        return iexVolume;
    }

    @JsonProperty("iexVolume")
    public void setIexVolume(Double iexVolume) {
        this.iexVolume = iexVolume;
    }

    @JsonProperty("lastTradeTime")
    public Double getLastTradeTime() {
        return lastTradeTime;
    }

    @JsonProperty("lastTradeTime")
    public void setLastTradeTime(Double lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    @JsonProperty("latestPrice")
    public Double getLatestPrice() {
        return latestPrice;
    }

    @JsonProperty("latestPrice")
    public void setLatestPrice(Double latestPrice) {
        this.latestPrice = latestPrice;
    }

    @JsonProperty("latestSource")
    public String getLatestSource() {
        return latestSource;
    }

    @JsonProperty("latestSource")
    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    @JsonProperty("latestTime")
    public String getLatestTime() {
        return latestTime;
    }

    @JsonProperty("latestTime")
    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    @JsonProperty("latestUpdate")
    public Double getLatestUpdate() {
        return latestUpdate;
    }

    @JsonProperty("latestUpdate")
    public void setLatestUpdate(Double latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    @JsonProperty("latestVolume")
    public Double getLatestVolume() {
        return latestVolume;
    }

    @JsonProperty("latestVolume")
    public void setLatestVolume(Double latestVolume) {
        this.latestVolume = latestVolume;
    }

    @JsonProperty("low")
    public Double getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(Double low) {
        this.low = low;
    }

    @JsonProperty("lowSource")
    public String getLowSource() {
        return lowSource;
    }

    @JsonProperty("lowSource")
    public void setLowSource(String lowSource) {
        this.lowSource = lowSource;
    }

    @JsonProperty("lowTime")
    public Double getLowTime() {
        return lowTime;
    }

    @JsonProperty("lowTime")
    public void setLowTime(Double lowTime) {
        this.lowTime = lowTime;
    }

    @JsonProperty("marketCap")
    public Double getMarketCap() {
        return marketCap;
    }

    @JsonProperty("marketCap")
    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    @JsonProperty("oddLotDelayedPrice")
    public Double getOddLotDelayedPrice() {
        return oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPrice")
    public void setOddLotDelayedPrice(Double oddLotDelayedPrice) {
        this.oddLotDelayedPrice = oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public Double getOddLotDelayedPriceTime() {
        return oddLotDelayedPriceTime;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public void setOddLotDelayedPriceTime(Double oddLotDelayedPriceTime) {
        this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
    }

    @JsonProperty("open")
    public Double getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(Double open) {
        this.open = open;
    }

    @JsonProperty("openTime")
    public Double getOpenTime() {
        return openTime;
    }

    @JsonProperty("openTime")
    public void setOpenTime(Double openTime) {
        this.openTime = openTime;
    }

    @JsonProperty("openSource")
    public String getOpenSource() {
        return openSource;
    }

    @JsonProperty("openSource")
    public void setOpenSource(String openSource) {
        this.openSource = openSource;
    }

    @JsonProperty("peRatio")
    public Double getPeRatio() {
        return peRatio;
    }

    @JsonProperty("peRatio")
    public void setPeRatio(Double peRatio) {
        this.peRatio = peRatio;
    }

    @JsonProperty("previousClose")
    public Float getPreviousClose() {
        return previousClose;
    }

    @JsonProperty("previousClose")
    public void setPreviousClose(Float previousClose) {
        this.previousClose = previousClose;
    }

    @JsonProperty("previousVolume")
    public Double getPreviousVolume() {
        return previousVolume;
    }

    @JsonProperty("previousVolume")
    public void setPreviousVolume(Double previousVolume) {
        this.previousVolume = previousVolume;
    }

    @JsonProperty("primaryExchange")
    public String getPrimaryExchange() {
        return primaryExchange;
    }

    @JsonProperty("primaryExchange")
    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("volume")
    public Double getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @JsonProperty("week52High")
    public Double getWeek52High() {
        return week52High;
    }

    @JsonProperty("week52High")
    public void setWeek52High(Double week52High) {
        this.week52High = week52High;
    }

    @JsonProperty("week52Low")
    public Double getWeek52Low() {
        return week52Low;
    }

    @JsonProperty("week52Low")
    public void setWeek52Low(Double week52Low) {
        this.week52Low = week52Low;
    }

    @JsonProperty("ytdChange")
    public Double getYtdChange() {
        return ytdChange;
    }

    @JsonProperty("ytdChange")
    public void setYtdChange(Double ytdChange) {
        this.ytdChange = ytdChange;
    }

    @JsonProperty("isUSMarketOpen")
    public Boolean getIsUSMarketOpen() {
        return isUSMarketOpen;
    }

    @JsonProperty("isUSMarketOpen")
    public void setIsUSMarketOpen(Boolean isUSMarketOpen) {
        this.isUSMarketOpen = isUSMarketOpen;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Quote.class.getName()).append('@').append(Double.toHexString(System.identityHashCode(this))).append('[');
        sb.append("avgTotalVolume");
        sb.append('=');
        sb.append(((this.avgTotalVolume == null)?"<null>":this.avgTotalVolume));
        sb.append(',');
        sb.append("calculationPrice");
        sb.append('=');
        sb.append(((this.calculationPrice == null)?"<null>":this.calculationPrice));
        sb.append(',');
        sb.append("change");
        sb.append('=');
        sb.append(((this.change == null)?"<null>":this.change));
        sb.append(',');
        sb.append("changePercent");
        sb.append('=');
        sb.append(((this.changePercent == null)?"<null>":this.changePercent));
        sb.append(',');
        sb.append("close");
        sb.append('=');
        sb.append(((this.close == null)?"<null>":this.close));
        sb.append(',');
        sb.append("closeSource");
        sb.append('=');
        sb.append(((this.closeSource == null)?"<null>":this.closeSource));
        sb.append(',');
        sb.append("closeTime");
        sb.append('=');
        sb.append(((this.closeTime == null)?"<null>":this.closeTime));
        sb.append(',');
        sb.append("companyName");
        sb.append('=');
        sb.append(((this.companyName == null)?"<null>":this.companyName));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("delayedPrice");
        sb.append('=');
        sb.append(((this.delayedPrice == null)?"<null>":this.delayedPrice));
        sb.append(',');
        sb.append("delayedPriceTime");
        sb.append('=');
        sb.append(((this.delayedPriceTime == null)?"<null>":this.delayedPriceTime));
        sb.append(',');
        sb.append("extendedChange");
        sb.append('=');
        sb.append(((this.extendedChange == null)?"<null>":this.extendedChange));
        sb.append(',');
        sb.append("extendedChangePercent");
        sb.append('=');
        sb.append(((this.extendedChangePercent == null)?"<null>":this.extendedChangePercent));
        sb.append(',');
        sb.append("extendedPrice");
        sb.append('=');
        sb.append(((this.extendedPrice == null)?"<null>":this.extendedPrice));
        sb.append(',');
        sb.append("extendedPriceTime");
        sb.append('=');
        sb.append(((this.extendedPriceTime == null)?"<null>":this.extendedPriceTime));
        sb.append(',');
        sb.append("high");
        sb.append('=');
        sb.append(((this.high == null)?"<null>":this.high));
        sb.append(',');
        sb.append("highSource");
        sb.append('=');
        sb.append(((this.highSource == null)?"<null>":this.highSource));
        sb.append(',');
        sb.append("highTime");
        sb.append('=');
        sb.append(((this.highTime == null)?"<null>":this.highTime));
        sb.append(',');
        sb.append("iexAskPrice");
        sb.append('=');
        sb.append(((this.iexAskPrice == null)?"<null>":this.iexAskPrice));
        sb.append(',');
        sb.append("iexAskSize");
        sb.append('=');
        sb.append(((this.iexAskSize == null)?"<null>":this.iexAskSize));
        sb.append(',');
        sb.append("iexBidPrice");
        sb.append('=');
        sb.append(((this.iexBidPrice == null)?"<null>":this.iexBidPrice));
        sb.append(',');
        sb.append("iexBidSize");
        sb.append('=');
        sb.append(((this.iexBidSize == null)?"<null>":this.iexBidSize));
        sb.append(',');
        sb.append("iexClose");
        sb.append('=');
        sb.append(((this.iexClose == null)?"<null>":this.iexClose));
        sb.append(',');
        sb.append("iexCloseTime");
        sb.append('=');
        sb.append(((this.iexCloseTime == null)?"<null>":this.iexCloseTime));
        sb.append(',');
        sb.append("iexLastUpdated");
        sb.append('=');
        sb.append(((this.iexLastUpdated == null)?"<null>":this.iexLastUpdated));
        sb.append(',');
        sb.append("iexMarketPercent");
        sb.append('=');
        sb.append(((this.iexMarketPercent == null)?"<null>":this.iexMarketPercent));
        sb.append(',');
        sb.append("iexOpen");
        sb.append('=');
        sb.append(((this.iexOpen == null)?"<null>":this.iexOpen));
        sb.append(',');
        sb.append("iexOpenTime");
        sb.append('=');
        sb.append(((this.iexOpenTime == null)?"<null>":this.iexOpenTime));
        sb.append(',');
        sb.append("iexRealtimePrice");
        sb.append('=');
        sb.append(((this.iexRealtimePrice == null)?"<null>":this.iexRealtimePrice));
        sb.append(',');
        sb.append("iexRealtimeSize");
        sb.append('=');
        sb.append(((this.iexRealtimeSize == null)?"<null>":this.iexRealtimeSize));
        sb.append(',');
        sb.append("iexVolume");
        sb.append('=');
        sb.append(((this.iexVolume == null)?"<null>":this.iexVolume));
        sb.append(',');
        sb.append("lastTradeTime");
        sb.append('=');
        sb.append(((this.lastTradeTime == null)?"<null>":this.lastTradeTime));
        sb.append(',');
        sb.append("latestPrice");
        sb.append('=');
        sb.append(((this.latestPrice == null)?"<null>":this.latestPrice));
        sb.append(',');
        sb.append("latestSource");
        sb.append('=');
        sb.append(((this.latestSource == null)?"<null>":this.latestSource));
        sb.append(',');
        sb.append("latestTime");
        sb.append('=');
        sb.append(((this.latestTime == null)?"<null>":this.latestTime));
        sb.append(',');
        sb.append("latestUpdate");
        sb.append('=');
        sb.append(((this.latestUpdate == null)?"<null>":this.latestUpdate));
        sb.append(',');
        sb.append("latestVolume");
        sb.append('=');
        sb.append(((this.latestVolume == null)?"<null>":this.latestVolume));
        sb.append(',');
        sb.append("low");
        sb.append('=');
        sb.append(((this.low == null)?"<null>":this.low));
        sb.append(',');
        sb.append("lowSource");
        sb.append('=');
        sb.append(((this.lowSource == null)?"<null>":this.lowSource));
        sb.append(',');
        sb.append("lowTime");
        sb.append('=');
        sb.append(((this.lowTime == null)?"<null>":this.lowTime));
        sb.append(',');
        sb.append("marketCap");
        sb.append('=');
        sb.append(((this.marketCap == null)?"<null>":this.marketCap));
        sb.append(',');
        sb.append("oddLotDelayedPrice");
        sb.append('=');
        sb.append(((this.oddLotDelayedPrice == null)?"<null>":this.oddLotDelayedPrice));
        sb.append(',');
        sb.append("oddLotDelayedPriceTime");
        sb.append('=');
        sb.append(((this.oddLotDelayedPriceTime == null)?"<null>":this.oddLotDelayedPriceTime));
        sb.append(',');
        sb.append("open");
        sb.append('=');
        sb.append(((this.open == null)?"<null>":this.open));
        sb.append(',');
        sb.append("openTime");
        sb.append('=');
        sb.append(((this.openTime == null)?"<null>":this.openTime));
        sb.append(',');
        sb.append("openSource");
        sb.append('=');
        sb.append(((this.openSource == null)?"<null>":this.openSource));
        sb.append(',');
        sb.append("peRatio");
        sb.append('=');
        sb.append(((this.peRatio == null)?"<null>":this.peRatio));
        sb.append(',');
        sb.append("previousClose");
        sb.append('=');
        sb.append(((this.previousClose == null)?"<null>":this.previousClose));
        sb.append(',');
        sb.append("previousVolume");
        sb.append('=');
        sb.append(((this.previousVolume == null)?"<null>":this.previousVolume));
        sb.append(',');
        sb.append("primaryExchange");
        sb.append('=');
        sb.append(((this.primaryExchange == null)?"<null>":this.primaryExchange));
        sb.append(',');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("volume");
        sb.append('=');
        sb.append(((this.volume == null)?"<null>":this.volume));
        sb.append(',');
        sb.append("week52High");
        sb.append('=');
        sb.append(((this.week52High == null)?"<null>":this.week52High));
        sb.append(',');
        sb.append("week52Low");
        sb.append('=');
        sb.append(((this.week52Low == null)?"<null>":this.week52Low));
        sb.append(',');
        sb.append("ytdChange");
        sb.append('=');
        sb.append(((this.ytdChange == null)?"<null>":this.ytdChange));
        sb.append(',');
        sb.append("isUSMarketOpen");
        sb.append('=');
        sb.append(((this.isUSMarketOpen == null)?"<null>":this.isUSMarketOpen));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }


    @Override
    public Object getId() {
        return this.symbol;
    }

    @Override
    public void setId(Object o) {
        this.symbol = String.valueOf(o);
    }
}
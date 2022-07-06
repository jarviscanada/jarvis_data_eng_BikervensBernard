package ca.jrvs.apps.trading.model;
/**
 * https://iexcloud.io/docs/api/#quote
 */

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
public class IexQuoteModel {

    @JsonProperty("avgTotalVolume")
    private Integer avgTotalVolume;
    @JsonProperty("calculationPrice")
    private String calculationPrice;
    @JsonProperty("change")
    private Double change;
    @JsonProperty("changePercent")
    private Double changePercent;
    @JsonProperty("close")
    private Object close;
    @JsonProperty("closeSource")
    private String closeSource;
    @JsonProperty("closeTime")
    private Object closeTime;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("delayedPrice")
    private Object delayedPrice;
    @JsonProperty("delayedPriceTime")
    private Object delayedPriceTime;
    @JsonProperty("extendedChange")
    private Object extendedChange;
    @JsonProperty("extendedChangePercent")
    private Object extendedChangePercent;
    @JsonProperty("extendedPrice")
    private Object extendedPrice;
    @JsonProperty("extendedPriceTime")
    private Object extendedPriceTime;
    @JsonProperty("high")
    private Object high;
    @JsonProperty("highSource")
    private Object highSource;
    @JsonProperty("highTime")
    private Object highTime;
    @JsonProperty("iexAskPrice")
    private Integer iexAskPrice;
    @JsonProperty("iexAskSize")
    private Integer iexAskSize;
    @JsonProperty("iexBidPrice")
    private Integer iexBidPrice;
    @JsonProperty("iexBidSize")
    private Integer iexBidSize;
    @JsonProperty("iexClose")
    private Double iexClose;
    @JsonProperty("iexCloseTime")
    private Long iexCloseTime;
    @JsonProperty("iexLastUpdated")
    private Long iexLastUpdated;
    @JsonProperty("iexMarketPercent")
    private Double iexMarketPercent;
    @JsonProperty("iexOpen")
    private Double iexOpen;
    @JsonProperty("iexOpenTime")
    private Long iexOpenTime;
    @JsonProperty("iexRealtimePrice")
    private Double iexRealtimePrice;
    @JsonProperty("iexRealtimeSize")
    private Integer iexRealtimeSize;
    @JsonProperty("iexVolume")
    private Integer iexVolume;
    @JsonProperty("lastTradeTime")
    private Long lastTradeTime;
    @JsonProperty("latestPrice")
    private Double latestPrice;
    @JsonProperty("latestSource")
    private String latestSource;
    @JsonProperty("latestTime")
    private String latestTime;
    @JsonProperty("latestUpdate")
    private Long latestUpdate;
    @JsonProperty("latestVolume")
    private Object latestVolume;
    @JsonProperty("low")
    private Object low;
    @JsonProperty("lowSource")
    private Object lowSource;
    @JsonProperty("lowTime")
    private Object lowTime;
    @JsonProperty("marketCap")
    private Long marketCap;
    @JsonProperty("oddLotDelayedPrice")
    private Object oddLotDelayedPrice;
    @JsonProperty("oddLotDelayedPriceTime")
    private Object oddLotDelayedPriceTime;
    @JsonProperty("open")
    private Object open;
    @JsonProperty("openTime")
    private Object openTime;
    @JsonProperty("openSource")
    private String openSource;
    @JsonProperty("peRatio")
    private Double peRatio;
    @JsonProperty("previousClose")
    private Double previousClose;
    @JsonProperty("previousVolume")
    private Integer previousVolume;
    @JsonProperty("primaryExchange")
    private String primaryExchange;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("volume")
    private Object volume;
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

    @JsonProperty("avgTotalVolume")
    public Integer getAvgTotalVolume() {
        return avgTotalVolume;
    }

    @JsonProperty("avgTotalVolume")
    public void setAvgTotalVolume(Integer avgTotalVolume) {
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
    public Object getClose() {
        return close;
    }

    @JsonProperty("close")
    public void setClose(Object close) {
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
    public Object getCloseTime() {
        return closeTime;
    }

    @JsonProperty("closeTime")
    public void setCloseTime(Object closeTime) {
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
    public Object getDelayedPrice() {
        return delayedPrice;
    }

    @JsonProperty("delayedPrice")
    public void setDelayedPrice(Object delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    @JsonProperty("delayedPriceTime")
    public Object getDelayedPriceTime() {
        return delayedPriceTime;
    }

    @JsonProperty("delayedPriceTime")
    public void setDelayedPriceTime(Object delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    @JsonProperty("extendedChange")
    public Object getExtendedChange() {
        return extendedChange;
    }

    @JsonProperty("extendedChange")
    public void setExtendedChange(Object extendedChange) {
        this.extendedChange = extendedChange;
    }

    @JsonProperty("extendedChangePercent")
    public Object getExtendedChangePercent() {
        return extendedChangePercent;
    }

    @JsonProperty("extendedChangePercent")
    public void setExtendedChangePercent(Object extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    @JsonProperty("extendedPrice")
    public Object getExtendedPrice() {
        return extendedPrice;
    }

    @JsonProperty("extendedPrice")
    public void setExtendedPrice(Object extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    @JsonProperty("extendedPriceTime")
    public Object getExtendedPriceTime() {
        return extendedPriceTime;
    }

    @JsonProperty("extendedPriceTime")
    public void setExtendedPriceTime(Object extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    @JsonProperty("high")
    public Object getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(Object high) {
        this.high = high;
    }

    @JsonProperty("highSource")
    public Object getHighSource() {
        return highSource;
    }

    @JsonProperty("highSource")
    public void setHighSource(Object highSource) {
        this.highSource = highSource;
    }

    @JsonProperty("highTime")
    public Object getHighTime() {
        return highTime;
    }

    @JsonProperty("highTime")
    public void setHighTime(Object highTime) {
        this.highTime = highTime;
    }

    @JsonProperty("iexAskPrice")
    public Integer getIexAskPrice() {
        return iexAskPrice;
    }

    @JsonProperty("iexAskPrice")
    public void setIexAskPrice(Integer iexAskPrice) {
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
    public Integer getIexBidPrice() {
        return iexBidPrice;
    }

    @JsonProperty("iexBidPrice")
    public void setIexBidPrice(Integer iexBidPrice) {
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
    public Long getIexCloseTime() {
        return iexCloseTime;
    }

    @JsonProperty("iexCloseTime")
    public void setIexCloseTime(Long iexCloseTime) {
        this.iexCloseTime = iexCloseTime;
    }

    @JsonProperty("iexLastUpdated")
    public Long getIexLastUpdated() {
        return iexLastUpdated;
    }

    @JsonProperty("iexLastUpdated")
    public void setIexLastUpdated(Long iexLastUpdated) {
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
    public Long getIexOpenTime() {
        return iexOpenTime;
    }

    @JsonProperty("iexOpenTime")
    public void setIexOpenTime(Long iexOpenTime) {
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
    public Integer getIexVolume() {
        return iexVolume;
    }

    @JsonProperty("iexVolume")
    public void setIexVolume(Integer iexVolume) {
        this.iexVolume = iexVolume;
    }

    @JsonProperty("lastTradeTime")
    public Long getLastTradeTime() {
        return lastTradeTime;
    }

    @JsonProperty("lastTradeTime")
    public void setLastTradeTime(Long lastTradeTime) {
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
    public Long getLatestUpdate() {
        return latestUpdate;
    }

    @JsonProperty("latestUpdate")
    public void setLatestUpdate(Long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    @JsonProperty("latestVolume")
    public Object getLatestVolume() {
        return latestVolume;
    }

    @JsonProperty("latestVolume")
    public void setLatestVolume(Object latestVolume) {
        this.latestVolume = latestVolume;
    }

    @JsonProperty("low")
    public Object getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(Object low) {
        this.low = low;
    }

    @JsonProperty("lowSource")
    public Object getLowSource() {
        return lowSource;
    }

    @JsonProperty("lowSource")
    public void setLowSource(Object lowSource) {
        this.lowSource = lowSource;
    }

    @JsonProperty("lowTime")
    public Object getLowTime() {
        return lowTime;
    }

    @JsonProperty("lowTime")
    public void setLowTime(Object lowTime) {
        this.lowTime = lowTime;
    }

    @JsonProperty("marketCap")
    public Long getMarketCap() {
        return marketCap;
    }

    @JsonProperty("marketCap")
    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    @JsonProperty("oddLotDelayedPrice")
    public Object getOddLotDelayedPrice() {
        return oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPrice")
    public void setOddLotDelayedPrice(Object oddLotDelayedPrice) {
        this.oddLotDelayedPrice = oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public Object getOddLotDelayedPriceTime() {
        return oddLotDelayedPriceTime;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public void setOddLotDelayedPriceTime(Object oddLotDelayedPriceTime) {
        this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
    }

    @JsonProperty("open")
    public Object getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(Object open) {
        this.open = open;
    }

    @JsonProperty("openTime")
    public Object getOpenTime() {
        return openTime;
    }

    @JsonProperty("openTime")
    public void setOpenTime(Object openTime) {
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
    public Double getPreviousClose() {
        return previousClose;
    }

    @JsonProperty("previousClose")
    public void setPreviousClose(Double previousClose) {
        this.previousClose = previousClose;
    }

    @JsonProperty("previousVolume")
    public Integer getPreviousVolume() {
        return previousVolume;
    }

    @JsonProperty("previousVolume")
    public void setPreviousVolume(Integer previousVolume) {
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
    public Object getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(Object volume) {
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
        sb.append(IexQuoteModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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

}
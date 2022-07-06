package ca.jrvs.apps.trading.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticker {
    @JsonProperty("quote")
    public IexQuoteModel getQuote() {return this.quote; }

    @JsonAnySetter
    public void setQuote(IexQuoteModel quote) {this.quote = quote; }

    IexQuoteModel quote;
}

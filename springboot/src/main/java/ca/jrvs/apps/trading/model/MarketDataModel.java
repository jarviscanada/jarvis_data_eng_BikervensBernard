package ca.jrvs.apps.trading.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.Map;

public class MarketDataModel {
    private Map<String, Ticker> additionalProperties = new HashMap<String, Ticker>();

    @JsonAnyGetter
    public Map<String, Ticker> getAdditionalProperties() {return this.additionalProperties;}
}

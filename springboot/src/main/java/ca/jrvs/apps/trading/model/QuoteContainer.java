package ca.jrvs.apps.trading.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "iexQuote"
})
public class QuoteContainer implements Serializable {

    @JsonProperty("iexQuote")
    private IexQuote iexQuote;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 20039204801853058L;

    @JsonProperty("iexQuote")
    public IexQuote getQuote() {
        return iexQuote;
    }

    @JsonProperty("iexQuote")
    public void setQuote(IexQuote iexQuote) {
        this.iexQuote = iexQuote;
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
        sb.append(QuoteContainer.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("iexQuote");
        sb.append('=');
        sb.append(((this.iexQuote == null)?"<null>":this.iexQuote));
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
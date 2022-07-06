package ca.jrvs.apps.trading.model.helper;
@org.springframework.stereotype.Service
public class MarketDataConfig {
    //URI Symbols
    public static final String QUERY_SYM = "?";
    public static final String PERCENT = "%";
    public static final String AMPERSAND = "&";
    public static final String EQUAL = "=";
    public static final int HTTP_OK = 200;
    private String host;
    private String token;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

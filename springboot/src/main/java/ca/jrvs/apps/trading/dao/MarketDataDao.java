package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.IexQuote;
import ca.jrvs.apps.trading.model.helper.JsonParser;
import ca.jrvs.apps.trading.model.helper.MarketDataConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@org.springframework.stereotype.Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "stock/market/batch?";
    private final String IEX_BATCH_URL;

    private final Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private final HttpClientConnectionManager httpClientConnectionManager;
    private final MarketDataConfig marketDataConfig;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig ) {
        this.httpClientConnectionManager = httpClientConnectionManager;
        this.marketDataConfig = marketDataConfig;
        IEX_BATCH_URL = this.marketDataConfig.getHost() + IEX_BATCH_PATH;
    }
    private CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(httpClientConnectionManager)
                .setConnectionManagerShared(true)
                .build();
    }
    private Optional<CloseableHttpResponse> executeHttpGet(HttpGet httpGet) {

        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient closeableHttpClient = this.getHttpClient();
        try {
            httpResponse = closeableHttpClient.execute(httpGet);
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.of(httpResponse);
    }
    @Override
    public Optional<IexQuote> findById(String ticker) {
        List<IexQuote> quotes = (List<IexQuote>) findAllById(Collections.singletonList(ticker.toUpperCase()));
        if (quotes == null || quotes.size() == 0) {
            return Optional.empty();
        } else if (quotes.size() == 1) {
            return Optional.of((quotes.get(0)));
        } else {throw new DataRetrievalFailureException("Unexpected number of quotes");}
    }
    @Override
    public Iterable<IexQuote> findAllById(Iterable<String> iterable) {
        ArrayList<IexQuote> list = new ArrayList<>();

        // build request/URI
        List nameValuePairs = new ArrayList();
        iterable.forEach((quote) -> nameValuePairs.add(new BasicNameValuePair("symbols",quote)));
        nameValuePairs.add(new BasicNameValuePair("types", "quote"));
        nameValuePairs.add(new BasicNameValuePair("token", this.marketDataConfig.getToken()));
        HttpGet httpGet = new HttpGet(this.IEX_BATCH_URL);
        try {
            httpGet.setURI( new URIBuilder(httpGet.getURI()).addParameters(nameValuePairs).build() );
            logger.info("url: "+httpGet.getURI());
            logger.info("display header:");
            Arrays.stream(httpGet.getAllHeaders()).forEach(i->logger.info( i.toString()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // Execute request
        CloseableHttpResponse httpResponse = executeHttpGet(httpGet).orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));

        // Parse if response valid
        if (httpResponse != null) {
            if (httpResponse.getStatusLine().getStatusCode() != this.marketDataConfig.HTTP_OK) {
                try {
                    HttpEntity e = httpResponse.getEntity();
                    logger.error(EntityUtils.toString(e));
                } catch (IOException e) {
                    logger.error("Response has no entity");
                }
                throw new RuntimeException("HTTP status error: " + httpResponse.getStatusLine().getStatusCode());
            }

            if (httpResponse.getEntity() == null) {
                logger.error("Response has no entity");
                throw new RuntimeException("Empty response body");
            }

            // HAPPY PATH

            try {
                //Array of JSON documents
                String jsonStr = EntityUtils.toString(httpResponse.getEntity());
                JSONObject IexQuotesJson = new JSONObject(jsonStr);

                //Get number of documents
                if (IexQuotesJson.length() == 0) {
                    throw new IllegalArgumentException("Invalid ticker");
                }

                IexQuotesJson.toMap().forEach((k,v) -> {
                    try {
                        HashMap x = (HashMap) v;
                        IexQuote q = JsonParser.toObjectFromJson(JsonParser.toJson(x.get("quote"),true,true), IexQuote.class);
                        list.add(q);

                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error Processing JsonParser.toJson", e);
                    } catch (IOException e) {
                        throw new RuntimeException("Error Processing JsonParser.toObjectFromJson", e);
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
    @Override
    public <S extends IexQuote> S save(S s) {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public boolean existsById(String s) {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public Iterable<IexQuote> findAll() {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public long count() {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public void deleteById(String s) {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public void delete(IexQuote quoteContainer) {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public void deleteAll() {throw new UnsupportedOperationException("Not implemented");}
}

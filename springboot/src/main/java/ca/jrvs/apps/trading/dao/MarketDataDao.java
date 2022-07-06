package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.dao.helper.JsonParser;
import ca.jrvs.apps.trading.model.IexQuoteModel;
import ca.jrvs.apps.trading.model.MarketDataModel;
import ca.jrvs.apps.trading.model.helper.MarketDataConfig;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Repository
public class MarketDataDao implements CrudRepository<IexQuoteModel, String> {
    private static final String IEX_BATCH_PATH = "stock/market/batch?";
    private final String IEX_BATCH_URL;

    private final Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private final HttpClientConnectionManager httpClientConnectionManager;
    private final MarketDataConfig marketDataConfig;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig) {
        this.httpClientConnectionManager = httpClientConnectionManager;
        this.marketDataConfig = marketDataConfig;
        IEX_BATCH_URL = this.marketDataConfig.getHost() + IEX_BATCH_PATH;
    }

    private CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(this.httpClientConnectionManager)
                .setConnectionManagerShared(true).build();
    }

    /**
     * Get a IexQuoteModel
     * @param ticker
     * @throws DataRetrievalFailureException if HTTP request failed
     * */
    @Override
    public Optional<IexQuoteModel> findById(String ticker) {
        Optional<IexQuoteModel> quote;
        List<IexQuoteModel> quotes = (List<IexQuoteModel>) findAllById(Collections.singletonList(ticker.toLowerCase()));
        if (quotes == null || quotes.size() == 0) {
            return Optional.empty();
        } else if (quotes.size() == 1) {
            quote = Optional.of((quotes.get(0)));
        } else {throw new DataRetrievalFailureException("Unexpected number of quotes");}
        return quote;
    }
    @Override
    public Iterable<IexQuoteModel> findAllById(Iterable<String> iterable) {

        ArrayList<IexQuoteModel> list = new ArrayList<>();

        // build uri string
        List nameValuePairs = new ArrayList();
        iterable.forEach((quote) -> nameValuePairs.add(new BasicNameValuePair("symbols",quote)));
        nameValuePairs.add(new BasicNameValuePair("types", "quote"));
        nameValuePairs.add(new BasicNameValuePair("token", this.marketDataConfig.getToken()));
        HttpGet httpGet = new HttpGet(this.IEX_BATCH_URL);

        CloseableHttpResponse httpResponse = null;
        try {
            httpGet.setURI( new URIBuilder(httpGet.getURI()).addParameters(nameValuePairs).build() );
            System.out.println(httpGet.getURI().toString());
            CloseableHttpClient client = this.getHttpClient();
            httpResponse =  client.execute(httpGet);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

        int status =  httpResponse.getStatusLine().getStatusCode();
        if (httpResponse != null) {

            if (status != this.marketDataConfig.HTTP_OK) {
                try {
                    HttpEntity e = httpResponse.getEntity();
                    System.out.println(EntityUtils.toString(e));
                    logger.error(EntityUtils.toString(e));
                } catch (IOException e) {
                    System.out.println("Response has no entity");
                    logger.error("Response has no entity");
                }
                throw new RuntimeException("HTTP status error: " + status);
            }

            if (httpResponse.getEntity() == null) {
                logger.error("Response has no entity");
                throw new RuntimeException("Empty response body");
            }

            // HAPPY PATH
            try {

                String jsonStr;
                HttpEntity e = httpResponse.getEntity();
                jsonStr = EntityUtils.toString(httpResponse.getEntity());
                MarketDataModel y = JsonParser.toObjectFromJson(jsonStr, MarketDataModel.class);
                y.getAdditionalProperties().values().forEach((payload) -> list.add(payload.getQuote()));
            } catch (IOException e) {
                throw new RuntimeException("Entity to String conversion failed", e);
            }
        }
        return list;
    }

    @Override
    public <S extends IexQuoteModel> S save(S s) {
        throw new UnsupportedOperationException("Not implemented");
    }
    @Override
    public <S extends IexQuoteModel> Iterable<S> saveAll(Iterable<S> iterable) {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public boolean existsById(String s) {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public Iterable<IexQuoteModel> findAll() {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public long count() {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public void deleteById(String s) {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public void delete(IexQuoteModel iexQuote) {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public void deleteAll(Iterable<? extends IexQuoteModel> iterable) {throw new UnsupportedOperationException("Not implemented");}
    @Override
    public void deleteAll() {throw new UnsupportedOperationException("Not implemented");}
}

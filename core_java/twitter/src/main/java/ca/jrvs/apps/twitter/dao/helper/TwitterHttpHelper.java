package ca.jrvs.apps.twitter.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;

public class TwitterHttpHelper implements HttpHelper {
    private OAuthConsumer consumer;
    private HttpClient httpClient;

    public OAuthConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(OAuthConsumer consumer) {
        this.consumer = consumer;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public TwitterHttpHelper(String consumerKey, String consumerSecret, String aToken, String aSecret) {
        this.consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
        consumer.setTokenWithSecret(aToken,aSecret);
        httpClient = HttpClientBuilder.create().build();
    }

    @Override
    public HttpResponse httpPost(URI uri) {

        //http get request
        HttpPost request = new HttpPost(uri);

        //sign the request (add header)
        try {
            consumer.sign(request);
        } catch (OAuthMessageSignerException e) {
            throw new RuntimeException(e);
        } catch (OAuthExpectationFailedException e) {
            throw new RuntimeException(e);
        } catch (OAuthCommunicationException e) {
            throw new RuntimeException(e);
        }
        //send request
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public HttpResponse httpGet(URI uri) {
        HttpGet request = new HttpGet(uri);

        //sign the request (add header)
        try {
            consumer.sign(request);
        } catch (OAuthMessageSignerException e) {
            throw new RuntimeException(e);
        } catch (OAuthExpectationFailedException e) {
            throw new RuntimeException(e);
        } catch (OAuthCommunicationException e) {
            throw new RuntimeException(e);
        }
        //send request
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}

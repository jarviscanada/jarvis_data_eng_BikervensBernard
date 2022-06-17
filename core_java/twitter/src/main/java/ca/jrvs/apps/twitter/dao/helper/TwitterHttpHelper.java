package ca.jrvs.apps.twitter.dao.helper;

import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpMethod;

public class TwitterHttpHelper implements HttpHelper {

  /**
   * Dependencies
   */
  private final OAuthConsumer consumer;
  private final HttpClient httpClient;

  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
                           String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    /**
     * Default = single connection
     */
    httpClient = new DefaultHttpClient();
  }

  /**
   * Execute a HTTP Post call
   *
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpPost(URI uri) {
    try {
      return postRequest(uri, HttpMethod.POST, null);
    } catch (RuntimeException e) {
      throw new RuntimeException("Failed to execute", e);
    }
  }

  /**
   * Execute a HTTP Get call
   *
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpGet(URI uri) {
    try {
      return postRequest(uri, HttpMethod.GET, null);
    } catch (RuntimeException e) {
      throw new RuntimeException("Failed to execute", e);
    }
  }

  private HttpResponse postRequest(URI uri, HttpMethod method, StringEntity stringEntity) {
    if (method == HttpMethod.GET) {
      HttpGet request = new HttpGet(uri);
      return signRequest(request);
    } else if (method == HttpMethod.POST) {
      HttpPost request = new HttpPost(uri);
      if (stringEntity != null) {
        request.setEntity(stringEntity);
      }
      return signRequest(request);
    }
    throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
  }

  /**
   * Sign and execute http request given correct auth
   *
   * @param request HttpRequestBase i.e. HttpPost, HttpGet
   * @return response body and header
   */
  private HttpResponse signRequest(HttpRequestBase request) {
    try {
      consumer.sign(request);
    } catch (OAuthMessageSignerException | OAuthCommunicationException |
             OAuthExpectationFailedException e) {
      throw new RuntimeException(e);
    }
    try {
      return httpClient.execute(request);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

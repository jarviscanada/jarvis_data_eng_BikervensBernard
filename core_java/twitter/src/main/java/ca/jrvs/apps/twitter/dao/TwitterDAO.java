package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterDAO implements CrdDao<Tweet, String> {

  //URI Constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";

  //URI Symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Response Code
  private static final int HTTP_OK = 200;

  private final HttpHelper httpHelper;
  private final JsonParser jsonParser;
  private final PercentEscaper percentEscaper = new PercentEscaper("", false);

  @Autowired
  public TwitterDAO(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
    this.jsonParser = new JsonParser();
  }

  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param tweet entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet tweet) {
    URI uri;
    try {
      uri = getPostUri(tweet);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    HttpResponse response = httpHelper.httpPost(uri);

    return parseResponse(response, HTTP_OK);
  }

  private URI getPostUri(Tweet tweet) throws URISyntaxException {
    StringBuilder uriBuilder = new StringBuilder(API_BASE_URI);
    uriBuilder.append(POST_PATH);
    uriBuilder.append(QUERY_SYM + "status" + EQUAL);
    uriBuilder.append(percentEscaper.escape(tweet.getText()));

    Coordinates coordinates = tweet.getCoordinates();
    if (coordinates != null) {
      uriBuilder.append(AMPERSAND);
      uriBuilder.append("lat=");
      uriBuilder.append(coordinates.getCoordinates().get(0).toString());
      uriBuilder.append(AMPERSAND);
      uriBuilder.append("long=");
      uriBuilder.append(coordinates.getCoordinates().get(1).toString());
    }
    return new URI(uriBuilder.toString());
  }

  public Tweet parseResponse(HttpResponse response, Integer expectedCode) {
    Tweet tweet = null;

    int status = response.getStatusLine().getStatusCode();
    if (status != expectedCode) {
      try {
        HttpEntity e = response.getEntity();
        System.out.println(EntityUtils.toString(e));
      } catch (IOException e) {
        System.out.println("Response has no entity");
      }
      throw new RuntimeException("HTTP status error: " + status);
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body");
    }

    String jsonStr;
    try {
      HttpEntity e = response.getEntity();
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Entity to String conversion failed", e);
    }

    try {
      tweet = (Tweet) JsonParser.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("JSON String to Object conversion failed", e);
    }

    return tweet;
  }

  /**
   * Find an entity(Tweet) by its id
   *
   * @param s entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String s) {
    URI uri;
    try {
      uri = getGetUri(s);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet id", e);
    }

    HttpResponse response = httpHelper.httpGet(uri);

    return parseResponse(response, HTTP_OK);
  }

  private URI getGetUri(String id) throws URISyntaxException {
    StringBuilder uriBuilder = new StringBuilder(API_BASE_URI);
    uriBuilder.append(SHOW_PATH);
    uriBuilder.append(QUERY_SYM + "id" + EQUAL);
    uriBuilder.append(id);
    return new URI(uriBuilder.toString());
  }

  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param s of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String s) {
    URI uri;
    try {
      uri = getDelUri(s);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet id", e);
    }

    HttpResponse response = httpHelper.httpPost(uri);

    return parseResponse(response, HTTP_OK);
  }

  private URI getDelUri(String id) throws URISyntaxException {
    StringBuilder uriBuilder = new StringBuilder(API_BASE_URI);
    uriBuilder.append(DELETE_PATH);
    uriBuilder.append("/");
    uriBuilder.append(id);
    uriBuilder.append(".json");
    return new URI(uriBuilder.toString());
  }
}

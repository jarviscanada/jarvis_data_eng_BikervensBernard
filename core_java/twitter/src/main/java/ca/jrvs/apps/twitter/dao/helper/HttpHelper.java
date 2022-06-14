package ca.jrvs.apps.twitter.dao.helper;
import java.net.URI;
import org.apache.http.HttpResponse;

public interface HttpHelper {

    final static String CONSUMER_KEY = System.getenv("consumerKey");
    final static String CONSUMER_SECRET = System.getenv("consumerSecret");
    final static String ACCESS_TOKEN = System.getenv("accessToken");
    final static String TOKEN_SECRET = System.getenv("tokenSecret");

    final static String API_V1_ENDPOINT = "https://api.twitter.com/1.1/";

    /**
     * Execute a HTTP Post call
     * @param uri
     * @return
     */
    HttpResponse httpPost(URI uri);

    /**
     * Execute a HTTP Get call
     * @param uri
     * @return
     */
    HttpResponse httpGet(URI uri);
}

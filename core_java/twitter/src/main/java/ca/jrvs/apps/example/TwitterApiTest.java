package ca.jrvs.apps.example;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.OAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;

public class TwitterApiTest {
    public static void main(String[] args) throws Exception {
        TwitterHttpHelper helper = new TwitterHttpHelper(
                HttpHelper.CONSUMER_KEY, HttpHelper.CONSUMER_SECRET,
                HttpHelper.ACCESS_TOKEN, HttpHelper.TOKEN_SECRET
        );

        OAuthConsumer consumer = helper.getConsumer();
        //http get request
        String status = "today is a good day";
        PercentEscaper percentEscaper = new PercentEscaper("",false);
        String str = HttpHelper.API_V1_ENDPOINT+"statuses/update.json=";
        HttpPost request = new HttpPost(str+percentEscaper.escape(status));

        //sign the request (add header)
        consumer.sign(request);
        System.out.println("http request header:");
        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

        //send request
        HttpResponse response = helper.getHttpClient().execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}

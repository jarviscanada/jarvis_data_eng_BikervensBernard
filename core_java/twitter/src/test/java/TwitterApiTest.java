import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;

public class TwitterApiTest {
    public static void main(String[] args) throws Exception {
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
                IConnectToTwitterApi.CONSUMER_KEY,
                IConnectToTwitterApi.CONSUMER_SECRET
        );

        //http get request
        String status = "today is a good day";
        PercentEscaper percentEscaper = new PercentEscaper("",false);
        HttpPost request = new HttpPost(""+percentEscaper.escape(status));

        //sign the request (add header)
        consumer.sign(request);
        System.out.println("http request header:");
        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

        //send request
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}

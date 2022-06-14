package ca.jrvs.apps.twitter.example;

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
                "jYBPOSPwnqnxfmBY5Drpon8p9",
                "N2lLhDzjQE9Hz1SLvnthRlOQPF8Ctmvy9S0BezJoLhG9B4ShWI"
        );
        consumer.setTokenWithSecret("1486392172647788546-clwXBjX3ilPZy7Xn5xmzKZVcbfKmnZ","vv5Bt849i9HHheBZNboIJ6s3FgP4VhMIpojEb28muDe7r");

        //http get request
        String status = "today is a good day";
        PercentEscaper percentEscaper = new PercentEscaper("",false);
        String str = "https://api.twitter.com/1.1/statuses/update.json=";
        HttpPost request = new HttpPost(str+percentEscaper.escape(status));

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

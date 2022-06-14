package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TwitterHttpHelperTest{

    @Test
    public void httpPost() {
        TwitterHttpHelper helper = new TwitterHttpHelper(
                HttpHelper.CONSUMER_KEY, HttpHelper.CONSUMER_SECRET,
                HttpHelper.ACCESS_TOKEN, HttpHelper.TOKEN_SECRET
        );
        String text = "hello_world";
        HttpResponse response = null;
        try {
            response = helper.httpPost(
                    new URI(HttpHelper.API_V1_ENDPOINT+"statuses/update.json?status="+text)
            );
           String str = EntityUtils.toString(response.getEntity());
           Assert.assertTrue(str.contains(text));
        } catch (URISyntaxException e) {
            Assert.fail();
            throw new RuntimeException(e);
        } catch (IOException e) {
            Assert.fail();
            throw new RuntimeException(e);
        }
    }

    @Test
    public void httpGet() {
    }
}
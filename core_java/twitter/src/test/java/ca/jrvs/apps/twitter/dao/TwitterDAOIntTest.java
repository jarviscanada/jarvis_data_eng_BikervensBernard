package ca.jrvs.apps.twitter.dao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterDAOIntTest{
    private static String id;
    private static long time = System.currentTimeMillis();
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");
    HttpHelper helper = new TwitterHttpHelper(
            CONSUMER_KEY,CONSUMER_SECRET,
            ACCESS_TOKEN,TOKEN_SECRET
    );
    private TwitterDAO dao = new TwitterDAO(helper);

    @Test
    public void create() {
        String hashtag = "#testing #twitterapi";
        String text = "@Avril_laqueer good morning " + hashtag + " created at: " + time;
        Double lat = 37.76893497;
        Double lon = -122.42284884;
        Tweet post = new Tweet();
        post.setText(text);
        Coordinates x = new Coordinates();
        List<Double> l = new ArrayList<>();
        l.add(lat);l.add(lon);
        x.setCoordinates(l);
        post.setCoordinates(x);

        HttpHelper helper = new TwitterHttpHelper(
                CONSUMER_KEY,CONSUMER_SECRET,
                ACCESS_TOKEN,TOKEN_SECRET
        );
        this.dao = new TwitterDAO(helper);

        Tweet responseTweet = dao.create(post);

        assertEquals(text, responseTweet.getText());
        assertNotNull(responseTweet.getCoordinates());
        assertEquals(2, responseTweet.getCoordinates().getCoordinates().size());
        assertEquals(lat, responseTweet.getCoordinates().getCoordinates().get(1));
        assertEquals(lon, responseTweet.getCoordinates().getCoordinates().get(0));

        assertTrue(hashtag.contains(responseTweet.getEntities().getHashtags().get(0).getText()));

        id = responseTweet.getIdStr();
    }

    @Test
    public void findById() {
    }

    @Test
    public void deleteById() {
    }
}

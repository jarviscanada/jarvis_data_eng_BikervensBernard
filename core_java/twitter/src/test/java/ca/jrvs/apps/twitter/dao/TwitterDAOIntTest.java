package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Entities;
import ca.jrvs.apps.twitter.model.Hashtag;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.annotation.Order;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterDAOIntTest{
    private static String id;
    private Tweet post ;
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
    private String hashtag = "#testing #twitterapi";
    private String text = "@Avril_laqueer good morning " + hashtag + " created at: " + time;
    private Double lat = 37.76893497;
    private Double lon = -122.42284884;
    private Coordinates x = new Coordinates();
    private List<Double> l = new ArrayList<>();

    @Before
    public void setup() {
        post = new Tweet();
        post.setText(text);
        if (id != null) {
            post.setId(new BigInteger(id));
            post.setIdStr(id);
        }
        x = new Coordinates();
        l = new ArrayList<>();
        l.add(lat);l.add(lon);
        x.setCoordinates(l);
        post.setCoordinates(x);

        Entities entities = new Entities();
        Hashtag h1 = new Hashtag();
        Hashtag h2 = new Hashtag();
        h1.setText("testing");
        h2.setText("twitterapi");
        List<Hashtag> h = new ArrayList<>();
        h.add(h1);h.add(h2);
        entities.setHashtags(h);
        post.setEntities(entities);
    }

    @Test
    @Order(1)
    public void create() {

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
    @Order(2)
    public void findById() {

        HttpHelper helper = new TwitterHttpHelper(
                CONSUMER_KEY,CONSUMER_SECRET,
                ACCESS_TOKEN,TOKEN_SECRET
        );
        this.dao = new TwitterDAO(helper);

        Tweet responseTweet = dao.findById(id);

        assertEquals(text, responseTweet.getText());
        assertNotNull(responseTweet.getCoordinates());
        assertEquals(2, responseTweet.getCoordinates().getCoordinates().size());
        assertEquals(lat, responseTweet.getCoordinates().getCoordinates().get(1));
        assertEquals(lon, responseTweet.getCoordinates().getCoordinates().get(0));
        assertTrue(hashtag.contains(responseTweet.getEntities().getHashtags().get(0).getText()));
    }

    @Test
    @Order(3)
    public void deleteById() {
        HttpHelper helper = new TwitterHttpHelper(
                CONSUMER_KEY,CONSUMER_SECRET,
                ACCESS_TOKEN,TOKEN_SECRET
        );
        this.dao = new TwitterDAO(helper);

        Tweet responseTweet = dao.deleteById(id);

        assertEquals(text, responseTweet.getText());
        assertNotNull(responseTweet.getCoordinates());
        assertEquals(2, responseTweet.getCoordinates().getCoordinates().size());
        assertEquals(lat, responseTweet.getCoordinates().getCoordinates().get(1));
        assertEquals(lon, responseTweet.getCoordinates().getCoordinates().get(0));
        assertTrue(hashtag.contains(responseTweet.getEntities().getHashtags().get(0).getText()));
    }
}

package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.*;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.Order;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterControllerIntTest{

    private static String id = "";
    private static long time = System.currentTimeMillis();

    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");
    private TwitterDAO dao = new TwitterDAO(new TwitterHttpHelper(
            CONSUMER_KEY,CONSUMER_SECRET,
            ACCESS_TOKEN,TOKEN_SECRET
    ));
    private String hashtag = "#testing #twitterapi";
    private String text = "@Avril_laqueer good morning " + hashtag + " created at: " + time;
    private Double lat = 37.76893497;
    private Double lon = -122.42284884;
    private Coordinates x = new Coordinates();
    private List<Double> l = new ArrayList<>();
    private Controller controller = new TwitterController(new TwitterService(this.dao));

    @Test
    @Order(1)
    public void postTweet() {
        Tweet response = this.controller.postTweet(new String[]{"post", text, lat+":"+lon});
        assertTrue(response != null);
        id = response.getIdStr();
    }

    @Test
    @Order(2)
    public void showTweet() {
        Tweet response = this.controller.showTweet(new String[]{"show","1539056805053186048","text,coordinates,entities"});
        assertTrue(response.getText().contains("good morning #testing #twitterapi created at: "));
        assertEquals(lon,response.getCoordinates().getCoordinates().get(0));
        assertEquals(lat,response.getCoordinates().getCoordinates().get(1));
        assertEquals("testing",response.getEntities().getHashtags().get(0).getText());
        assertEquals("twitterapi",response.getEntities().getHashtags().get(1).getText());

        assertEquals(null,response.getIdStr());
        assertEquals(null,response.getId());
        assertEquals(null,response.getCreated_at());
        assertEquals(0,response.getRetweetCount());
        assertEquals(0,response.getFavoriteCount());
        assertEquals(null,response.getFavorited());
        assertEquals(null,response.getRetweeted());
    }

    @Test
    @Order(3)
    public void deleteTweet() {
        Tweet tweet = this.controller.postTweet(new String[]{"post", text, lat+":"+lon});
        assertTrue(tweet != null);
        id = tweet.getIdStr();
        List<Tweet> response = this.controller.deleteTweet(new String[] {"delete",id});
        assertEquals(1,response.size());
    }
}
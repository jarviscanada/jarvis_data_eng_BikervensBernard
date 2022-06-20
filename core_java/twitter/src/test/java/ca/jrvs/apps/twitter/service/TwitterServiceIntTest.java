package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.annotation.Order;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterServiceIntTest{

    private static String id = "1538873580456497156";
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

    private TwitterService service = new TwitterService(new TwitterDAO(helper));

    private String hashtag = "#testing #twitterapi";
    private String at = "Avril_laqueer";
    private String text = "@"+at+" hello, friend! " + hashtag + " created at: " + time;
    private Double lat = 37.76893497;
    private Double lon = -122.42284884;

    private Coordinates x = new Coordinates();
    private List<Double> l = new ArrayList<>();

    @Before
    public void setUp() {
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

        UserMention userMention = new UserMention();
        userMention.setScreenName(at);
        entities.setUserMentions(Collections.singletonList(userMention));
        post.setEntities(entities);

    }

    @Test
    @Order(1)
    public void post_Tweet() {
        Tweet responseTweet = this.service.postTweet(post);
        assertTrue(responseTweet!=null);
        this.id = responseTweet.getIdStr();
    }

    @Test
    @Order(2)
    public void showTweet() {
        String [] fields = new String[] {"created_at","id","id_str","text"};
        //missing field "entities"
        //missing field "retweet_count"
        //missing field "favorite_count"
        //missing field "coordinates"
        //missing field "favorited"
        //missing field "retweeted"
        Tweet tweet = service.showTweet(id, fields);
        assertTrue(tweet.getCreated_at()!=null);
        assertTrue(tweet.getId()!=null);
        assertTrue(tweet.getIdStr()!=null);
        assertTrue(tweet.getText()!=null);

        assertEquals(tweet.getEntities(),null);
        assertEquals(tweet.getRetweetCount(),0);
        assertEquals(tweet.getFavoriteCount(),0);
        assertEquals(tweet.getCoordinates(),null);
        assertTrue(tweet.getFavorited() == null);
        assertTrue(tweet.getRetweeted() == null);
    }

    @Test
    @Order(3)
    public void deleteTweets() {
        List<Tweet> tweets = service.deleteTweets(new String [] {id});
        assertFalse(tweets.isEmpty());
        assertTrue(tweets.size() == 1);
    }
}
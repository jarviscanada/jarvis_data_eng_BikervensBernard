package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Entities;
import ca.jrvs.apps.twitter.model.Hashtag;
import org.mockito.AdditionalMatchers;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class TwitterControllerUnitTest{

    private static long time = System.currentTimeMillis();
    private String CONSUMER_KEY = System.getenv("consumerKey");
    private String CONSUMER_SECRET = System.getenv("consumerSecret");
    private String ACCESS_TOKEN = System.getenv("accessToken");
    private String TOKEN_SECRET = System.getenv("tokenSecret");

    private String hashtag = "#testing #twitterapi";
    private String text = "@jack good morning " + hashtag + " created at: " + time;
    private Double lat = 37.76893497;
    private Double lon = -122.42284884;
    public Service service = new TwitterService(new TwitterDAO(new TwitterHttpHelper(
            CONSUMER_KEY,CONSUMER_SECRET, ACCESS_TOKEN,TOKEN_SECRET
    )));

    @InjectMocks
    public TwitterController controller = new TwitterController(service);

    @Test
    public void post_tweet_valid() {
        String valid = ":";
        Service spy = Mockito.spy(this.service);
        this.controller = new TwitterController(spy);

        doReturn(new Tweet()).when(spy).postTweet(any());

        Tweet response = this.controller.postTweet(new String[]{"post", text, lat+valid+lon});
        assertTrue(response != null);
    }

    @Test
    public void post_tweet_invalid() {
        Service spy = Mockito.spy(this.service);
        this.controller = new TwitterController(spy);
        String invalid = ",";
        doReturn(null).when(spy).postTweet(any());
        try {
            this.controller.postTweet(new String[]{"post", text, lat+invalid+lon});
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void post_tweet_invalidRange() {
        Service spy = Mockito.spy(this.service);
        this.controller = new TwitterController(spy);
        doReturn(null).when(spy).postTweet(any());
        try {
            this.controller.postTweet(new String[]{"post", text, 99999+":"+99999});
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void show_tweet_valid() {
        Service spy = Mockito.spy(this.service);
        this.controller = new TwitterController(spy);
        doReturn(new Tweet()).when(spy).showTweet(any(), aryEq(new String[]{"text","coordinates","entities"}));
        Tweet response = this.controller.showTweet(new String[]{"show","1539056805053186048","text,coordinates,entities"});
        assertTrue(response != null);
    }
}
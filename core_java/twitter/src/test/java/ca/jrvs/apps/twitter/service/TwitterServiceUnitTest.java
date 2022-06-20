package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
    private static String id = "1538873580456497156";
    private Tweet post ;
    private static long time = System.currentTimeMillis();
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");
    @Mock
    HttpHelper mockHelper = new TwitterHttpHelper(
            CONSUMER_KEY,CONSUMER_SECRET,
            ACCESS_TOKEN,TOKEN_SECRET
    );
    private TwitterService service = new TwitterService(new TwitterDAO(mockHelper));
    private String hashtag = "#testing #twitterapi";
    private String at = "jack";
    private String text = "@"+at+" hello, friend! I hope all is good with you!!!!!" + hashtag + " created at: " + time+"ms";
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
        post.setCreated_at(String.valueOf(time));
    }

    @Test
    public void post_tweet_valid() throws IOException {
        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Wed Jun 15 16:00:51 +0000 2022\",\n"
                + "   \"id\":1537102846759419906,\n"
                + "   \"id_str\":\"1537102846759419906\",\n"
                + "   \"text\":\""+text+"\",\n"
                + "   \"entities\":{\n"
                + "      \"hashtags\":[{\"text\":\"testing\",\"indices\":[28,36]},{\"text\":\"twitterapi\",\"indices\":[37,48]}],"
                + "      \"user_mentions\":[{\"screen_name\":\"jack\",\"name\":\"X \\u00c6 A-12 stan\",\"id\":2391628200,\"id_str\":\"2391628200\",\"indices\":[0,14]}]"
                + "   },\n"
                + "   \"geo\":{" +
                "\"type\":\"Point\",\"coordinates\":[37.76893497,-122.42284884]"
                +    "}," +
                "\"coordinates\":{" +
                "\"type\":\"Point\"," +
                "\"coordinates\":[-122.42284884,37.76893497]" +
                "},"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorited\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";

        //instantiate dao with mock
        TwitterDAO dao = new TwitterDAO(mockHelper);
        //create a spy of instantiated dao (dao was instantiated with mockHelper)
        TwitterDAO spyDao = Mockito.spy(dao);
        //correct output as expectedTweet
        Tweet expectedTweetFromJson = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);

        //mock returns null and do nothing when httpPost is called
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        /* create() is called on spy -> httpPost() is called in create() and returns null ->
           parseResponse() is called on spy and returns the correct output as expectedTweet */
        doReturn(expectedTweetFromJson).when(spyDao).parseResponse(any(),eq(200));
        Tweet output = service.postTweet(post);
        //code 200 is enough to assert "tweet creation"
        assertNotNull(output);
    }

    @Test
    public void post_tweet_invalidChar() {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        post.setText(s);
        Tweet t = service.postTweet(post);
        assertEquals(t,null);
    }

    @Test
    public void post_tweet_invalidCoordinateRange() {
        x = new Coordinates();
        l = new ArrayList<>();
        l.add(Double.valueOf(9999));l.add(99999.0);
        x.setCoordinates(l);
        post.setCoordinates(x);
        Tweet t = service.postTweet(post);
        assertEquals(t,null);
    }

    @Test
    public void show_tweet_valid() {
        String [] fields = new String[] {"created_at","id","id_str","text"};
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDAO spyDao = Mockito.spy(new TwitterDAO(mockHelper));

        doReturn(new Tweet()).when(spyDao).parseResponse(any(),any());
        doReturn(post).when(spyDao).findById(eq(id));
        service = new TwitterService(spyDao);

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
    public void show_tweet_invalidFields() {
        String invalidField = "password";
        String [] fields = new String[] {invalidField,"id","id_str","text"};
        Tweet tweet = service.showTweet(id, fields);
        assertEquals(tweet,null);
    }

    @Test
    public void show_tweet_invalidId() {
        String [] fields = new String[] {"id","id_str","text"};
        Tweet tweet = service.showTweet("1", fields);
        assertEquals(tweet,null);
    }

    @Test
    public void delete_tweets_invalidFormat() {
        String[]ids = new String[2];
        String newId = "invalid";
        Tweet newPost = new Tweet();
        ids[0] = id;ids[1] = newId;
        newPost.setIdStr(newId);

        TwitterDAO spyDao = Mockito.spy(new TwitterDAO(mockHelper));
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        doReturn(new Tweet()).when(spyDao).parseResponse(any(),any());
        doReturn(post).when(spyDao).findById(eq(id));
        doReturn(post).when(spyDao).deleteById(eq(id));
        service = new TwitterService(spyDao);
        List<Tweet> output = service.deleteTweets(ids);
        assertEquals(1,output.size());
    }

    @Test
    public void delete_tweets_valid() {
        String[]ids = new String[2];
        String newId = "1118877780456497156";
        Tweet newPost = new Tweet();
        ids[0] = id;ids[1] = newId;
        newPost.setId(new BigInteger(newId));
        newPost.setIdStr(newId);
        //instantiate dao with mock
        TwitterDAO dao = new TwitterDAO(mockHelper);
        //create a spy of instantiated dao (dao was instantiated with mockHelper)
        TwitterDAO spyDao = Mockito.spy(dao);
        service = new TwitterService(spyDao);

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        doReturn(new Tweet()).when(spyDao).parseResponse(any(),any());
        doReturn(post).when(spyDao).findById(eq(id));
        doReturn(post).when(spyDao).deleteById(eq(id));
        doReturn(newPost).when(spyDao).findById(eq(newId));
        doReturn(newPost).when(spyDao).deleteById(eq(newId));

        List<Tweet> output = service.deleteTweets(ids);
        assertEquals(output.size(),2);
    }
}

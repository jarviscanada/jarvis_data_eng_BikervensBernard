package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDAOUnitTest {

    @Mock
    HttpHelper mockHelper;
    @InjectMocks
    TwitterDAO dao;
    TwitterDAO newDao;
    Tweet createdTweet = new Tweet();
    long time = System.currentTimeMillis();
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(
            CONSUMER_KEY,CONSUMER_SECRET,
            ACCESS_TOKEN,TOKEN_SECRET
    );
    @Before
    public void buildTweet() {
        newDao = new TwitterDAO(httpHelper);
        String hashtag = "#testing #twitterapi";
        String text = "@Avril_laqueer get better soon " + hashtag + " created at: " + time + "ms";
        Double lat = 37.76893497;
        Double lon = -122.42284884;

        Coordinates coordinates = new Coordinates();
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(lat);
        doubleList.add(lon);
        coordinates.setCoordinates(doubleList);
        createdTweet.setText(text);
        createdTweet.setCoordinates(coordinates);
    }

    @Test
    public void showTweet() throws Exception {

        //should get exception here
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.create(createdTweet);
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        //Test a happy path
        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "   \"id\":1097607853932564480,\n"
                + "   \"id_str\":\"1097607853932564480\",\n"
                + "   \"text\":\"test with loc223\",\n"
                + "   \"entities\":{\n"
                + "      \"hashtags\":[],"
                + "      \"user_mentions\":[]"
                + "   },\n"
                + "   \"coordinates\":null,"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorited\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";

        //return and do nothing when httpPost is called on the mmock
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDAO spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);

        //mock parseResponse by return the expected tweet
        doReturn(expectedTweet).when(spyDao).parseResponse(any(), anyInt());
        Tweet tweet = spyDao.create(createdTweet);
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void createTest() throws Exception {

        String expectedMockTweet = "{\n"
                + "   \"created_at\":\"Wed Jun 15 16:00:51 +0000 2022\",\n"
                + "   \"id\":1537102846759419906,\n"
                + "   \"id_str\":\"1537102846759419906\",\n"
                + "   \"text\":\"@Avril_laqueer get better soon #testing #twitterapi created at: "+time+"ms\",\n"
                + "   \"entities\":{\n"
                + "      \"hashtags\":[{\"text\":\"testing\",\"indices\":[28,36]},{\"text\":\"twitterapi\",\"indices\":[37,48]}],"
                + "      \"user_mentions\":[{\"screen_name\":\"Avril_laqueer\",\"name\":\"X \\u00c6 A-12 stan\",\"id\":2391628200,\"id_str\":\"2391628200\",\"indices\":[0,14]}]"
                + "   },\n"
                + "   \"coordinates\":null,"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorited\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";

        //return and do nothing when httpPost is called on the mmock
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDAO spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(expectedMockTweet, Tweet.class);

        //mock parseResponse by return the expected tweet
        doReturn(expectedTweet).when(spyDao).parseResponse(any(), anyInt());
        Tweet tweet = spyDao.create(createdTweet);
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
        assertEquals(tweet.getText(),expectedTweet.getText());
        assertEquals(tweet.getIdStr(),expectedTweet.getIdStr());
    }
}
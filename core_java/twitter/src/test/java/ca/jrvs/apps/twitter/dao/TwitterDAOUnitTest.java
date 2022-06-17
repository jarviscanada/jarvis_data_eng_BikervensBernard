package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.*;

import java.io.IOException;
import java.math.BigInteger;
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
    Tweet createdTweet;
    long time = System.currentTimeMillis();

    @Before
    public void buildTweet() {
        createdTweet = new Tweet();

        String hashtag = "#testing #twitterapi";
        String text = "@jack get better soon " + hashtag + " created at: " + time + "ms";
        createdTweet.setText(text);

        Coordinates coordinates = new Coordinates();
        List<Double> doubleList = new ArrayList<>();
        Double lat = 37.76893497;
        Double lon = -122.42284884;
        doubleList.add(lat);doubleList.add(lon);
        coordinates.setCoordinates(doubleList);
        createdTweet.setCoordinates(coordinates);

        Entities entities = new Entities();
        Hashtag h1 = new Hashtag();
        Hashtag h2 = new Hashtag();
        h1.setText("testing");
        h2.setText("twitterapi");
        List<Hashtag> h = new ArrayList<>();
        h.add(h1);h.add(h2);
        entities.setHashtags(h);

        UserMention userMention = new UserMention();
        userMention.setName("jack");
        List<UserMention> u = new ArrayList<>();
        entities.setUserMentions(u);
        createdTweet.setEntities(entities);

        createdTweet.setId(new BigInteger("1097607853932564480"));
        createdTweet.setIdStr("1097607853932564480");
        createdTweet.setRetweetCount(0);
        createdTweet.setFavoriteCount(0);
        createdTweet.setFavorited(false);
        createdTweet.setRetweeted(false);
    }

    @Test
    public void showTweet() throws IOException {

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
                + "   \"text\":\"Hello world\",\n"
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

        //instantiate dao with mock
        dao = new TwitterDAO(mockHelper);
        //create a spy of instantiated dao (dao was instantiated with mockHelper)
        TwitterDAO spyDao = Mockito.spy(dao);
        //correct output as expectedTweet
        Tweet expectedTweetFromJson = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);

        //mock returns null and do nothing when httpPost is called
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        /* create() is called on spy -> httpPost() is called in create() and returns null ->
           parseResponse() is called on spy and returns the correct output as expectedTweet */
        doReturn(expectedTweetFromJson).when(spyDao).parseResponse(any(), anyInt());

        createdTweet.setText("Hello world");
        Tweet output = spyDao.create(createdTweet);
        assertNotNull(output);
        //equal data is enough to assert "tweet displaying"
        assertEquals(output.getText(),expectedTweetFromJson.getText());
        assertEquals(output.getIdStr(),expectedTweetFromJson.getIdStr());
    }

    @Test
    public void createTest() throws IOException {

        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Wed Jun 15 16:00:51 +0000 2022\",\n"
                + "   \"id\":1537102846759419906,\n"
                + "   \"id_str\":\"1537102846759419906\",\n"
                + "   \"text\":\"@jack get better soon #testing #twitterapi created at: "+time+"ms\",\n"
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
        dao = new TwitterDAO(mockHelper);
        //create a spy of instantiated dao (dao was instantiated with mockHelper)
        TwitterDAO spyDao = Mockito.spy(dao);
        //correct output as expectedTweet
        Tweet expectedTweetFromJson = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);

        //mock returns null and do nothing when httpPost is called
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        /* create() is called on spy -> httpPost() is called in create() and returns null ->
           parseResponse() is called on spy and returns the correct output as expectedTweet */
        doReturn(expectedTweetFromJson).when(spyDao).parseResponse(any(),eq(200));
        Tweet output = spyDao.create(createdTweet);
        //code 200 is enough to assert "tweet creation"
        assertNotNull(output);
    }

    @Test
    public void deleteById() throws IOException {

        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Wed Jun 15 16:00:51 +0000 2022\",\n"
                + "   \"id\":1537102846759419906,\n"
                + "   \"id_str\":\"1537102846759419906\",\n"
                + "   \"text\":\"@jack get better soon #testing #twitterapi created at: "+time+"ms\",\n"
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
        dao = new TwitterDAO(mockHelper);
        //create a spy of instantiated dao (dao was instantiated with mockHelper)
        TwitterDAO spyDao = Mockito.spy(dao);
        //correct output as expectedTweet
        Tweet expectedTweetFromJson = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);

        //mock returns null and do nothing when httpPost is called
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        /* create() is called on spy -> httpPost() is called in create() and returns null ->
           parseResponse() is called on spy and returns the correct output as expectedTweet */
        doReturn(expectedTweetFromJson).when(spyDao).parseResponse(any(),eq(200));
        Tweet output = spyDao.deleteById("1537102846759419906");

        assertNotNull(output);
        //equal data is enough to assert "found by id"
        assertEquals(output.getText(),expectedTweetFromJson.getText());
        assertEquals(output.getIdStr(),expectedTweetFromJson.getIdStr());
    }
}
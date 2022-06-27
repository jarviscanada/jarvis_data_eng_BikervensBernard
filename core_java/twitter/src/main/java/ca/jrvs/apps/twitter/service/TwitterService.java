package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@org.springframework.stereotype.Service
public class TwitterService implements Service{

    private TwitterDAO dao;
    private final static int TWITTER_CHAR_LIMIT = 140;
    private final static int LON_INDEX = 1;
    private final static int LAT_INDEX = 0;
    private final static Double LAT_RANGE = 90.0;
    private final static Double LON_RANGE = 180.0;
    @Autowired
    public TwitterService(TwitterDAO dao) {
        this.dao = dao;
    }

    /**
     * verify if given tweet has valid coordinate
     * @param tweet tweet to post
     * @return true if coordinate for longitude and latitude ir within valid geo range
     */
    private boolean isInRange(Tweet tweet) {
        Double lon = tweet.getCoordinates().getCoordinates().get(LON_INDEX);
        Double lat = tweet.getCoordinates().getCoordinates().get(LAT_INDEX);
        return (lat < LAT_RANGE) && lat > (-1 * LAT_RANGE) && lon < LON_RANGE && lon > (-1 * LON_RANGE);
    }

    /**
     * verify if given string does not exceed limit character
     * @param limit max character allowed
     * @param tweet tweet to post
     * @return true if given string does not exceed limit character else return false
     */
    private boolean isNotExceedingCharLimit(String tweet, int limit) {
       return (tweet.length() < TWITTER_CHAR_LIMIT) ? true : false;
    }

    /**
     * verify if given string can parse as new BigInteger
     * @param id string representing the id
     * @return true if given string can parse as new BigInteger else return false
     */
    private boolean isValidId(String id){
        try {
            new BigInteger(id);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Iterate over each given fields to see if they are possible valid field of a "Tweet" object
     * @param fields all possible field for a "Tweet" object
     * @return true if each field is a valid possible field for a "Tweet" object else return false
     */
    private boolean fieldsAreValid(String[] fields){
        for (String field : fields){
            boolean valid =
                    field.equals("created_at")
                    || field.equals("id")
                    || field.equals("id_str")
                    || field.equals("text")
                    || field.equals("entities")
                    || field.equals("coordinates")
                    || field.equals("retweet_count")
                    || field.equals("favorite_count")
                    || field.equals("favorited")
                    || field.equals("retweeted");

            if (!valid) return false;
        }
        return true;
    }

    @Override
    public Tweet postTweet(Tweet tweet) {
        boolean valid = (tweet != null && this.isNotExceedingCharLimit(tweet.getText(),140) && this.isInRange(tweet)) ? true : false;
        return (valid) ? this.dao.create(tweet): null;
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        Tweet tweet = null;
        try {
            if (this.isValidId(id)) {
                tweet = this.dao.findById(id);
            }
            //tweet = this.isValidId(id)? this.dao.findById(id): null;
            if (this.fieldsAreValid(fields) && tweet != null) {

                List<String> list = Arrays.asList(fields);
                //if we do not wish to display this field set it to null on object
                if (!list.contains("created_at")){tweet.setCreated_at(null);}
                if (!list.contains("id")){tweet.setId(null);}
                if (!list.contains("id_str")){tweet.setIdStr(null);}
                if (!list.contains("text")){tweet.setText(null);}
                if (!list.contains("entities")){tweet.setEntities(null);}
                if (!list.contains("coordinates")){tweet.setCoordinates(null);}
                if (!list.contains("retweet_count")){tweet.setRetweetCount(0);}
                if (!list.contains("favorite_count")){tweet.setFavoriteCount(0);}
                if (!list.contains("favorited")){tweet.setFavorited(null);}
                if (!list.contains("retweeted")){tweet.setRetweeted(null);}
            }
            else {return null;}
        } catch (IllegalArgumentException e) {return null;}
        catch (RuntimeException e) {return null;}
        return tweet;//serviced formatted tweet object
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> tweets = new ArrayList<>();
        for (String id: ids) {
            if (this.isValidId(id)) {
                Tweet tweet = this.dao.findById(id);
                if (tweet != null) {
                    this.dao.deleteById(tweet.getIdStr());
                    tweets.add(tweet);
                }
            }
        }
        return tweets;
    }
}

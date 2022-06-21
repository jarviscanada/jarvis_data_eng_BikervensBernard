package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.*;
import ca.jrvs.apps.twitter.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@org.springframework.stereotype.Controller
public class TwitterController implements Controller {

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";
    private static final String USAGE_FOR_POSTING = "USAGE: TwitterApp post \"text\" \"latitude:longitude\"";
    private static final String USAGE_FOR_SHOWING = "USAGE: TwitterApp show tweet_id \"field1,fields2,...\"";
    private static final String USAGE_FOR_DELETING = "USAGE: TwitterApp delete \"id1,id2,...\"";
    private final Service service;

    @Autowired
    public TwitterController(Service service) {this.service = service;}

    private Tweet buildTweet(String text, Double lat, Double lon) {

        Tweet post = new Tweet();
        post.setText(text);
        Coordinates x = new Coordinates();
        List<Double> l = new ArrayList<>();
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

        return post;
    }

    @Override
    public Tweet postTweet(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException(USAGE_FOR_POSTING);
        }

        String text = args[1];
        String coords = args[2];
        String[] coordArray = coords.split(COORD_SEP);
        if (coordArray.length != 2 || text.isEmpty()) {
            throw new IllegalArgumentException(USAGE_FOR_POSTING +". Longitude/latitude is require, tweet can't be blank");
        }
        Double lat = null;
        Double lon = null;
        try {
            lat = Double.parseDouble(coordArray[0]);
            lon = Double.parseDouble(coordArray[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(USAGE_FOR_POSTING +". Longitude/latitude malformed");
        }

        Tweet post = this.buildTweet(text, lat, lon);
        Tweet response = service.postTweet(post);
        if (response == null) {
            throw new IllegalArgumentException(USAGE_FOR_POSTING +". Longitude/latitude is require, tweet can't be blank");
        }
        else {
            return response;
        }
    }

    @Override
    public Tweet showTweet(String[] args) {
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException(USAGE_FOR_SHOWING);
        }

        String id = args[1];
        String field = "";
        String[] fields = {};
        if (args.length == 3) {
            field = args[2];
            if (field.isEmpty()) {
                throw new IllegalArgumentException(USAGE_FOR_SHOWING);
            }
            fields = field.split(COMMA);
        }
        if (id.isEmpty()) {throw new IllegalArgumentException(USAGE_FOR_SHOWING);}
        Tweet response = service.showTweet(id, fields);
        if (response == null) {
            throw new IllegalArgumentException(USAGE_FOR_SHOWING);
        }
        else {
            return response;
        }
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(USAGE_FOR_DELETING);
        }
        String[] ids = args[1].split(COMMA);
        return service.deleteTweets(ids);
    }
}
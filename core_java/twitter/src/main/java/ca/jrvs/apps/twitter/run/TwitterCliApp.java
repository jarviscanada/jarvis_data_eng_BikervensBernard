package ca.jrvs.apps.twitter.run;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component
public class TwitterCliApp{

    public static final String usage = "USAGE: TwitterCLIApp post|show|delete [options]";
    private Controller controller;

    @Autowired
    public TwitterCliApp(Controller controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        HttpHelper httpHelper = new TwitterHttpHelper(
                consumerKey, consumerSecret,
                accessToken,tokenSecret
        );
        CrdDao dao = new TwitterDAO(httpHelper);
        Service service = new TwitterService((TwitterDAO) dao);
        Controller controller = new TwitterController(service);
        TwitterCliApp app = new TwitterCliApp(controller);

        app.run(args);
    }

    public void run(String[] args) {
        if (args.length < 2){
            throw new IllegalArgumentException(usage);
        }
        switch (args[0].toLowerCase()) {
            case "post":
                printTweet(controller.postTweet(args));
                break;
            case "show":
                printTweet(controller.showTweet(args));
                break;
            case "delete":
                controller.deleteTweet(args).forEach(this::printTweet);
                break;
            default:
                throw new IllegalArgumentException(usage);
        }
    }

    private void printTweet(Tweet tweet) {
        try {
            System.out.println(JsonParser.toJson(tweet, true, true));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Object to String conversion failed", e);
        }
    }
}
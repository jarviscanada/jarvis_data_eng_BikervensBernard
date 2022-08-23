package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.run.TwitterCliApp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@ComponentScan(value = "ca.jrvs.apps.twitter")
public class TwitterCLIComponentScan {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(
                    TwitterCLIComponentScan.class
                );
        TwitterCliApp app = context.getBean(TwitterCliApp.class);
        app.run(args);
    }
}

package ca.jrvs.apps.trading.spring;

import ca.jrvs.apps.trading.controller.QuoteController;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication(
        scanBasePackages = "ca.jrvs.apps.trading",
        exclude = {
                JdbcTemplateAutoConfiguration.class,
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        })
//@Configuration
//@EnableTransactionManagement
public class SpringBoot implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(SpringBoot.class);

    //@Value("${app.init.dailyList}")
    //private String[] initDailyList;

    @Autowired
    private QuoteService quoteService;
    @Autowired
    private QuoteController quoteController;
    @Autowired
    private MarketDataDao marketDataDao;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBoot.class);
        app.setAllowBeanDefinitionOverriding(true);
        Environment env = new StandardEnvironment();

        //app.setEnvironment();
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        //MarketDataConfig marketDataConfig = new MarketDataConfig();
        //marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
        //marketDataConfig.setToken(System.getenv("token"));

        //PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //cm.setMaxTotal(50);
        //cm.setDefaultMaxPerRoute(50);
        //this.quoteService.findIexQuoteByTicker(args[0]);
        //new QuoteService(new MarketDataDao(cm,marketDataConfig));
        // new SpringBoot().run("aapl");
    }
}

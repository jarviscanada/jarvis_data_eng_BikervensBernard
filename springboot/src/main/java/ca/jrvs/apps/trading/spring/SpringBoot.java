package ca.jrvs.apps.trading.spring;

import ca.jrvs.apps.trading.controller.QuoteController;
import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication(
        scanBasePackages = "ca.jrvs.apps.trading",
        exclude = {
                JdbcTemplateAutoConfiguration.class,
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        })
@Configuration()
@EnableTransactionManagement
public class SpringBoot implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(SpringBoot.class);

    @Value("#{'${initDailyList}'.split(',')}")
    private String[] initDailyList;

    @Autowired
    private QuoteService quoteService;
    @Autowired
    private QuoteController quoteController;
    @Autowired
    private MarketDataDao marketDataDao;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBoot.class);
        app.setAllowBeanDefinitionOverriding(true);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.quoteService.saveQuotes(Arrays.asList(this.initDailyList));
    }
}

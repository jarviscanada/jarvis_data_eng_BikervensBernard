package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountEntityDao;
import ca.jrvs.apps.trading.dao.QuoteEntityDao;
import ca.jrvs.apps.trading.dao.TraderEntityDao;
import ca.jrvs.apps.trading.model.databaseEntity.AccountEntity;
import ca.jrvs.apps.trading.model.databaseEntity.TraderEntity;
import ca.jrvs.apps.trading.view.TraderAccountView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderAccountServiceIntTest {

    @Autowired
    private TraderAccountService service;
    @Autowired
    private AccountEntityDao accountEntityDao;
    @Autowired
    private TraderEntityDao traderEntityDao;
    @Autowired
    DataSource dataSource;

    @Value("${token}")
    private String token;
    @Value("${jdbcUrl}")
    private String URL;
    @Value("${PSQL_DB_TEST}")
    private String DB;
    @Value("${PSQL_USER}")
    private String PSQL_USER;
    @Value("${PSQL_PASSWORD}")
    private String PSQL_PASSWORD;

    private TraderEntity savedTraderEntity;
    private AccountEntity savedAccountEntity;
    @Before
    public void setUp() throws Exception {
        this.traderEntityDao = new TraderEntityDao(dataSource);
        if(!this.traderEntityDao.existsById(1)) {
            this.savedTraderEntity = new TraderEntity();
            this.savedTraderEntity.setFirstName("rick");
            this.savedTraderEntity.setLastName("sanchez");
            this.savedTraderEntity.setDob(Date.valueOf("2002-10-17"));
            this.savedTraderEntity.setCountry("Canada");
            this.savedTraderEntity.setEmail("rick@morty.com");
            this.savedTraderEntity = this.traderEntityDao.save(savedTraderEntity);
        }
        this.savedAccountEntity = new AccountEntity();
        this.savedAccountEntity.setTraderId(this.savedTraderEntity.getId());
        this.savedAccountEntity.setAmount(100000d);
        this.savedAccountEntity = this.accountEntityDao.save(this.savedAccountEntity);
    }

    @After
    public void tearDown() throws Exception {
        this.accountEntityDao.deleteAll();
        this.traderEntityDao.deleteAll();
        this.savedTraderEntity = new TraderEntity();
        this.savedAccountEntity = new AccountEntity();
    }

    @Test
    public void createTraderAndAccount() {
        TraderAccountView view = this.service.createTraderAndAccount(this.savedTraderEntity);
        assertTrue(view.getAccount().getAmount()==100000d);
        assertTrue(view.getAccount().getTraderId()==this.savedTraderEntity.getId());
        assertTrue(view.getAccount().getId()==this.savedAccountEntity.getId());

        assertTrue(view.getTrader().getEmail().equals(this.savedTraderEntity.getEmail()));
        assertTrue(view.getTrader().getCountry().equals(this.savedTraderEntity.getCountry()));
        assertTrue(view.getTrader().getFirstName().equals(this.savedTraderEntity.getFirstName()));
        assertTrue(view.getTrader().getLastName().equals(this.savedTraderEntity.getLastName()));
        assertTrue(view.getTrader().getDob().equals(this.savedTraderEntity.getDob()));
        assertTrue(view.getTrader().getId()==this.savedAccountEntity.getId());
    }

    @Test
    public void deleteTraderById() {
        assertTrue(this.traderEntityDao.findById(this.savedTraderEntity.getId()).isPresent());
        AccountEntity account = this.accountEntityDao.findByTraderId(this.savedTraderEntity.getId()).orElse(null);
        assertTrue(account!=null);
        account.setAmount(0d);
        this.accountEntityDao.save(account);
        this.service.deleteTraderById(this.savedTraderEntity.getId());
        assertFalse(this.traderEntityDao.findById(this.savedTraderEntity.getId()).isPresent());
    }

    @Test
    public void deposit() {
        Double before = this.savedAccountEntity.getAmount();
        this.savedAccountEntity = this.service.deposit(this.savedTraderEntity.getId(),2000d);
        assertTrue(this.savedAccountEntity.getAmount() == before+2000d);
    }

    @Test
    public void withdraw() {
        Double before = this.savedAccountEntity.getAmount();
        this.savedAccountEntity = this.service.withdraw(this.savedTraderEntity.getId(),1d);
        assertTrue(this.savedAccountEntity.getAmount() == before-1d);
    }
}
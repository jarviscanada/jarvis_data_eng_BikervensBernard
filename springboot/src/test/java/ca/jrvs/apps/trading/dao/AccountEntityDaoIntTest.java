package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.databaseEntity.AccountEntity;
import ca.jrvs.apps.trading.model.databaseEntity.TraderEntity;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class AccountEntityDaoIntTest {

    @Autowired
    DataSource dataSource;
    @Autowired
    private TraderEntityDao traderEntityDao;
    @Autowired
    private AccountEntityDao dao;
    private TraderEntity savedTraderEntity;
    private AccountEntity savedAccountEntity;

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

    @Before
    public void setUp() throws Exception {
        this.traderEntityDao = new TraderEntityDao(dataSource);
        int id =0;
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
        this.savedAccountEntity.setAmount(0d);
        this.savedAccountEntity = this.dao.save(this.savedAccountEntity);
    }

    @After
    public void tearDown() throws Exception {
        this.dao.deleteAll();
        this.traderEntityDao.deleteAll();
        this.savedTraderEntity = new TraderEntity();
        this.savedAccountEntity = new AccountEntity();
    }

    // (C)RUD
    /**
     * implicit test for "Create" : @Before needs to create an account so here we just verify that it exist
     * */
    @Test
    public void insertOne() {
        assertTrue(this.dao.existsById(1));
    }

    // C(R)UD
    @Test
    public void find() {
        ArrayList<AccountEntity> all = (ArrayList<AccountEntity>) this.dao.findAll();
        for (AccountEntity entity : all) {
            assertTrue(entity.getAmount().doubleValue() == 0d);
            assertTrue(entity.getId() == 1);
        }

        Optional<AccountEntity> a = this.dao.findById(1);
        assertTrue(a.isPresent());

        a = Optional.empty();
        a = this.dao.findByTraderId(1);
        assertTrue(a.isPresent());

        all = (ArrayList<AccountEntity>) this.dao.findAllById(Arrays.asList(1));
        for (AccountEntity entity : all) {
            assertTrue(entity.getAmount().doubleValue() == 0d);
            assertTrue(entity.getId() == 1);
        }
    }

    // CR(U)D
    @Test
    public void update() {
        AccountEntity a = this.dao.findByTraderId(1).orElse(null);
        assertTrue(a!=null);
        a.setAmount(200.10);
        this.dao.save(a);
        a = this.dao.findByTraderId(1).orElse(null);
        assertTrue(a!=null);
        assertTrue(a.getAmount() == 200.10);
    }

    // CRU(D)
    @Test
    public void delete() {
        assertTrue(this.dao.existsById(1));
        this.dao.deleteAll();
        this.traderEntityDao.deleteAll();
        assertFalse(this.dao.existsById(1));
    }
}
package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderEntityDaoIntTest {

    @Autowired
    DataSource dataSource;
    @Autowired
    private TraderEntityDao traderEntityDao;

    private TraderEntity savedTraderEntity;

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
        if(!this.traderEntityDao.existsById(1)) {
            this.savedTraderEntity = new TraderEntity();
            savedTraderEntity.setFirstName("rick");
            savedTraderEntity.setLastName("sanchez");
            savedTraderEntity.setDob(Date.valueOf("2002-10-17"));
            savedTraderEntity.setCountry("Canada");
            savedTraderEntity.setEmail("rick@morty.com");
            this.traderEntityDao.save(savedTraderEntity);
        }
    }

    @After
    public void tearDown() throws Exception {
        this.traderEntityDao.deleteAll();
        savedTraderEntity = new TraderEntity();
    }

    @Test
    public void getEntityClass() {
        assertTrue(this.savedTraderEntity.getClass().equals(this.traderEntityDao.getEntityClass()));
    }

    @Test
    public void addOne() {
        this.savedTraderEntity = new TraderEntity();
        savedTraderEntity.setFirstName("morty");
        savedTraderEntity.setLastName("sanchez");
        savedTraderEntity.setDob(Date.valueOf("2012-10-17"));
        savedTraderEntity.setCountry("Canada");
        savedTraderEntity.setEmail("morty@morty.com");
        assertTrue(this.traderEntityDao.save(savedTraderEntity)!=null);
    }

    @Test
    public void saveAll() {
        this.traderEntityDao.deleteAll();
        TraderEntity savedTraderEntity2 = new TraderEntity();
        savedTraderEntity2.setFirstName("morty");
        savedTraderEntity2.setLastName("sanchez");
        savedTraderEntity2.setDob(Date.valueOf("2012-10-17"));
        savedTraderEntity2.setCountry("Canada");
        savedTraderEntity2.setEmail("morty@morty.com");

        this.savedTraderEntity = new TraderEntity();
        savedTraderEntity.setFirstName("rick");
        savedTraderEntity.setLastName("sanchez");
        savedTraderEntity.setDob(Date.valueOf("2002-10-17"));
        savedTraderEntity.setCountry("Canada");
        savedTraderEntity.setEmail("rick@morty.com");

        this.traderEntityDao.saveAll(Arrays.asList(this.savedTraderEntity,savedTraderEntity2));
        assertTrue(((ArrayList<TraderEntity>)this.traderEntityDao.findAll()).size() == 2);
    }

    @Test
    public void findById() {
        assertTrue(this.traderEntityDao.findById(1).isPresent());
    }

    @Test
    public void existsById() {
        assertTrue(this.traderEntityDao.existsById(1));
    }

    @Test
    public void findAll() {
        this.traderEntityDao.deleteAll();
        TraderEntity savedTraderEntity2 = new TraderEntity();
        savedTraderEntity2.setFirstName("morty");
        savedTraderEntity2.setLastName("sanchez");
        savedTraderEntity2.setDob(Date.valueOf("2012-10-17"));
        savedTraderEntity2.setCountry("Canada");
        savedTraderEntity2.setEmail("morty@morty.com");

        this.savedTraderEntity = new TraderEntity();
        savedTraderEntity.setFirstName("rick");
        savedTraderEntity.setLastName("sanchez");
        savedTraderEntity.setDob(Date.valueOf("2002-10-17"));
        savedTraderEntity.setCountry("Canada");
        savedTraderEntity.setEmail("rick@morty.com");

        this.traderEntityDao.saveAll(Arrays.asList(this.savedTraderEntity,savedTraderEntity2));
        ArrayList<TraderEntity> all = (ArrayList<TraderEntity>) this.traderEntityDao.findAll();
        assertEquals(2, all.size());
    }

    @Test
    public void deleteById() {
        ArrayList<TraderEntity> all = (ArrayList<TraderEntity>) this.traderEntityDao.findAll();
        TraderEntity trader = all.get(0);
        int id = trader.getId();

        assertEquals(1, all.size());
        this.traderEntityDao.deleteById(id);
        all = (ArrayList<TraderEntity>) this.traderEntityDao.findAll();
        assertTrue(all.isEmpty());
    }

    @Test
    public void delete() {
        this.savedTraderEntity.setId(this.traderEntityDao.findAll().iterator().next().getId());
        ArrayList<TraderEntity> all = (ArrayList<TraderEntity>) this.traderEntityDao.findAll();
        assertFalse(all.isEmpty());
        this.traderEntityDao.delete(this.savedTraderEntity);
        all = (ArrayList<TraderEntity>) this.traderEntityDao.findAll();
        assertTrue(all.isEmpty());
    }

    @Test
    public void deleteAll() {
        ArrayList<TraderEntity> all = (ArrayList<TraderEntity>) this.traderEntityDao.findAll();
        assertFalse(all.isEmpty());

        this.traderEntityDao.deleteAll();
        all = (ArrayList<TraderEntity>) this.traderEntityDao.findAll();
        assertTrue(all.isEmpty());
    }
}
package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.TraderEntity;
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
            this.savedTraderEntity.setId(1);
            savedTraderEntity.setFirstName("rick");
            savedTraderEntity.setLastName("sanchez");
            int x =2000-1900;
            savedTraderEntity.setDob(Date.valueOf("2002-10-17"));
            savedTraderEntity.setCountry("Canada");
            savedTraderEntity.setEmail("rick@morty.com");
            this.traderEntityDao.save(savedTraderEntity);
        }
    }

    @After
    public void tearDown() throws Exception {
        savedTraderEntity = new TraderEntity();
        if(this.traderEntityDao.existsById(1)) {
            this.traderEntityDao.deleteById(1);
        }
    }

    @Test
    public void getEntityClass() {
        assertTrue(true);
    }

    @Test
    public void save() {
    }

    @Test
    public void saveAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void existsById() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findAllById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteAll() {
    }

    @Test
    public void testDeleteAll() {
    }
}
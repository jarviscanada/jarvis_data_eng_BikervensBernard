package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.databaseEntity.PositionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Repository
public class PositionEntityDao {
    //Position view is  read-only, so you need to disable create and update methods

    public static final String TABLE_NAME = "position";
    public static final String FK_ID_COLUMN = "account_id";
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private DataSource dataSource;

    @Autowired
    public PositionEntityDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert =
                new SimpleJdbcInsert(dataSource).
                        withTableName(this.getTableName()).
                        usingGeneratedKeyColumns(this.getIdColumnName());
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public SimpleJdbcInsert getSimpleJdbcInsert() {
        return this.simpleJdbcInsert;
    }

    public String getTableName() {
        return this.TABLE_NAME;
    }

    public String getIdColumnName() {
        return this.FK_ID_COLUMN;
    }

    public String getFKIdColumnName() {
        return this.FK_ID_COLUMN;
    }

    Class<PositionEntity> getEntityClass() {
        return PositionEntity.class;
    }

    public Optional<PositionEntity> findById(Integer accountid) {
        String selectSql = "SELECT * FROM " +this.getTableName()+" WHERE "+this.getFKIdColumnName()+"=?";
        List<PositionEntity> res = jdbcTemplate.query(selectSql, new Object[]{accountid}, BeanPropertyRowMapper.newInstance(this.getEntityClass()) );
        return Optional.of( res.get(0) );
    }

    public boolean existsById(Integer account_id) {
        String selectSql = "SELECT * FROM " + this.getTableName();
        List<PositionEntity> positions =  jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(this.getEntityClass()));
        for (PositionEntity positionEntity : positions) {
            if (positionEntity.getId() !=null && positionEntity.getId().equals(account_id)) {
                return true;// exist on table
            }
        }
        return false;
    }

    public Iterable<PositionEntity> findAll() {
        String selectSql = "SELECT * FROM " + this.getTableName();
        return jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(this.getEntityClass()));
    }

    public Iterable<PositionEntity> findAllById(Iterable<Integer> iterable) {
        String selectSql = "SELECT * FROM " + this.getTableName();
        List<PositionEntity> all = new ArrayList<>(jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(this.getEntityClass())));
        List<PositionEntity> filter = new ArrayList<>();
        for (Integer id: iterable) {
            all.stream().filter(traderEntity -> traderEntity.getId().equals(id)).forEach(filter::add);
        }
        return filter;
    }

    public long count() {
        Iterable<PositionEntity> all = this.findAll();
        return (all instanceof Collection) ? ((Collection<PositionEntity>) all).size() : Math.toIntExact(StreamSupport.stream(all.spliterator(), false).count());
    }
}

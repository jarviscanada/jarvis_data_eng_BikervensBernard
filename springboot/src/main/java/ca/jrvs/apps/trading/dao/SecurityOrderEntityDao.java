package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.databaseEntity.SecurityOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class SecurityOrderEntityDao extends JdbcCrudDao<SecurityOrderEntity>{
    public static final String TABLE_NAME = "security_order";
    public static final String ID_COLUMN = "id";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private DataSource dataSource;

    @Autowired
    public SecurityOrderEntityDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert =
                new SimpleJdbcInsert(dataSource).
                        withTableName(this.getTableName()).
                        usingGeneratedKeyColumns(this.getIdColumnName());
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {
        return this.simpleJdbcInsert;
    }

    @Override
    public String getTableName() {
        return this.TABLE_NAME;
    }

    @Override
    public String getIdColumnName() {
        return this.ID_COLUMN;
    }

    @Override
    Class<SecurityOrderEntity> getEntityClass() {
        return SecurityOrderEntity.class;
    }

    @Override
    public <S extends SecurityOrderEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends SecurityOrderEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<SecurityOrderEntity> findById(Integer integer) {
        return Optional.empty();
    }

    public <S extends SecurityOrderEntity> Iterable<S>  findAllForTraderId(Integer traderid) {
        String selectSql = "SELECT * FROM " +this.getTableName()+" WHERE account_id=?";
        List<SecurityOrderEntity> res = jdbcTemplate.query(selectSql, new Object[]{traderid}, BeanPropertyRowMapper.newInstance(SecurityOrderEntity.class) );
        return (Iterable<S>) res;
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<SecurityOrderEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<SecurityOrderEntity> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(SecurityOrderEntity securityOrderEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends SecurityOrderEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}

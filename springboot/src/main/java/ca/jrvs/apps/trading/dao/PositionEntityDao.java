package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.databaseEntity.PositionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.Optional;
@org.springframework.stereotype.Repository
public class PositionEntityDao extends JdbcCrudDao<PositionEntity> {
    //Position view is  read-only, so you need to disable create and update methods

    public static final String TABLE_NAME = "account";
    public static final String ID_COLUMN = "id";

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
    Class<PositionEntity> getEntityClass() {
        return PositionEntity.class;
    }

    @Override
    public <S extends PositionEntity> S save(S s) {
        return null;
    }

    @Override
    public <S extends PositionEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<PositionEntity> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<PositionEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<PositionEntity> findAllById(Iterable<Integer> iterable) {
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
    public void delete(PositionEntity positionEntity) {

    }

    @Override
    public void deleteAll(Iterable<? extends PositionEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}

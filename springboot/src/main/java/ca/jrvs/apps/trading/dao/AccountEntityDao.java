package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.databaseEntity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Repository
public class AccountEntityDao extends JdbcCrudDao<AccountEntity> {
    //account

    public static final String TABLE_NAME = "account";
    public static final String ID_COLUMN = "id";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private DataSource dataSource;

    @Autowired
    public AccountEntityDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert =
                new SimpleJdbcInsert(dataSource).
                        withTableName(getTableName()).
                        usingGeneratedKeyColumns(getIdColumnName());
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {
        return simpleJdbcInsert;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getIdColumnName() {
        return ID_COLUMN;
    }

    @Override
    Class<AccountEntity> getEntityClass() {
        return AccountEntity.class;
    }

    @Override
    public <S extends AccountEntity> S save(S accountEntity) {
        if(existsById(accountEntity.getId())) {
            int updatedRowNo = updateOne(accountEntity);
            if (updatedRowNo != 1) {
                throw new DataRetrievalFailureException("unable to update accountEntity");
            }
        } else {
            addOne(accountEntity);
        }
        return accountEntity;
    }
    private <S extends AccountEntity> int addOne(S accountEntity) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(accountEntity);
        int row = simpleJdbcInsert.execute(parameterSource);
        if (row != 1) {
            throw new IncorrectResultSizeDataAccessException("Failed to insert",1,row);
        }
        return row;
    }
    private <S extends AccountEntity> int updateOne(S accountEntity) {
        String update_sql = "UPDATE "+getTableName()+" SET amount=? WHERE trader_id=?";
        return jdbcTemplate.update(
                update_sql, new Object[]{accountEntity.getAmount(),accountEntity.getTraderId()}
        );
    }

    @Override
    public <S extends AccountEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        iterable.forEach(a -> {
            save(a);
        });
        ArrayList<AccountEntity> added = new ArrayList<>();
        findAll().forEach(a -> added.add(a));
        return (Iterable<S>) added;
    }

    @Override
    public Optional<AccountEntity> findById(Integer id) {
        String selectSql = "SELECT * FROM " +getTableName()+" WHERE trader_id=?";
        List<AccountEntity> res = jdbcTemplate.query(selectSql, new Object[]{id}, BeanPropertyRowMapper.newInstance(AccountEntity.class) );
        return Optional.of( res.get(0) );
    }

    @Override
    public boolean existsById(Integer id) {
        String selectSql = "SELECT * FROM " + getTableName();
        List<AccountEntity> quotes =  jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(AccountEntity.class));
        for (AccountEntity quote : quotes) {
            if (quote.getId() !=null && quote.getId().equals(id)) {
                return true;// exist on table
            }
        }
        return false;
    }

    @Override
    public Iterable<AccountEntity> findAll() {
        String selectSql = "SELECT * FROM " + getTableName();
        return jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(AccountEntity.class));

    }

    @Override
    public Iterable<AccountEntity> findAllById(Iterable<Integer> iterable) {
        String selectSql = "SELECT * FROM " + getTableName();
        List<AccountEntity> all = new ArrayList<>(jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(AccountEntity.class)));
        List<AccountEntity> filter = new ArrayList<>();
        for (Integer id: iterable) {
            all.stream().filter(traderEntity -> traderEntity.getId().equals(id)).forEach(filter::add);
        }
        return filter;
    }

    @Override
    public long count() {
        Iterable<AccountEntity> all = findAll();
        return (all instanceof Collection) ? ((Collection<AccountEntity>) all).size() : Math.toIntExact(StreamSupport.stream(all.spliterator(), false).count());

    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null");
        }
        String deleteSql = "DELETE FROM " + getTableName() + " WHERE  trader_id =?";
        int row = jdbcTemplate.update(deleteSql, id);
        if (row < 1) {
            throw new IncorrectResultSizeDataAccessException("should be more than 1",1);
        }
    }

    public void deleteByTraderId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null");
        }
        String deleteSql = "DELETE FROM " + getTableName() + " WHERE trader_id =?";
        if (jdbcTemplate.update(deleteSql, id) != 1) {
            throw new IncorrectResultSizeDataAccessException(1);
        }
    }

    @Override
    public void delete(AccountEntity accountEntity) {
        deleteById(accountEntity.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends AccountEntity> iterable) {
        iterable.forEach(q->deleteById(q.getId()));
    }

    @Override
    public void deleteAll() {
        Iterable<AccountEntity> all = findAll();
        all.forEach(q->deleteById(q.getId()));
    }
}

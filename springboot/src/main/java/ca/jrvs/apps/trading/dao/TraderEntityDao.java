package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.databaseEntity.TraderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Repository
public class TraderEntityDao extends JdbcCrudDao<TraderEntity> {

    public static final String TABLE_NAME = "trader";
    public static final String ID_COLUMN = "id";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private DataSource dataSource;

    @Autowired
    public TraderEntityDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert =
                new SimpleJdbcInsert(dataSource).
                withTableName(this.getTableName()).
                usingGeneratedKeyColumns(this.getIdColumnName());
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {return this.jdbcTemplate;}

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {return this.simpleJdbcInsert;}

    @Override
    public String getTableName() {return this.TABLE_NAME;}

    @Override
    public String getIdColumnName() {return this.ID_COLUMN;}

    @Override
    Class<TraderEntity> getEntityClass() {return TraderEntity.class;}

    @Override
    public <S extends TraderEntity> S save(S trader) {
        if(existsById(trader.getId())) {
            int updatedRowNo = this.updateOne(trader);
            if (updatedRowNo != 1) {
                throw new DataRetrievalFailureException("unable to update trader");
            }
        } else {
            this.addOne(trader);
        }
        return trader;
    }
    private <S extends TraderEntity> int addOne(S traderEntity) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(traderEntity);
        int row = simpleJdbcInsert.execute(parameterSource);
        if (row != 1) {
            throw new IncorrectResultSizeDataAccessException("Failed to insert",1,row);
        }
        return row;
    }
    private <S extends TraderEntity> int updateOne(S traderEntity) {
        String update_sql = "UPDATE quote SET first_name=?, last_name=?, "
                + "dob=?, country=?, email=? WHERE id=?";
        return jdbcTemplate.update(update_sql, makeUpdateValues( traderEntity ));
    }
    private Object[] makeUpdateValues(TraderEntity traderEntity) {
        return new Object[]{
                traderEntity.getFirstName(),
                traderEntity.getLastName(),
                traderEntity.getDob(),
                traderEntity.getCountry(),
                traderEntity.getEmail(),
                traderEntity.getId()
        };
    }

    @Override
    public <S extends TraderEntity> Iterable<S> saveAll(Iterable<S> traders) {
        traders.forEach(trader -> {
            this.save(trader);
        });
        ArrayList<TraderEntity> added = new ArrayList<>();
        this.findAll().forEach(traderEntity -> added.add(traderEntity));
        return (Iterable<S>) added;
    }

    @Override
    public Optional<TraderEntity> findById(Integer id) {
        String selectSql = "SELECT * FROM " +this.getTableName()+" WHERE id=?";
        List<TraderEntity> res = jdbcTemplate.query(selectSql, new Object[]{id}, BeanPropertyRowMapper.newInstance(TraderEntity.class) );
        return Optional.of( res.get(0) );
    }

    @Override
    public boolean existsById(Integer id) {
        String selectSql = "SELECT * FROM " + this.getTableName();
        List<TraderEntity> quotes =  jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(TraderEntity.class));
        for (TraderEntity quote : quotes) {
            if (quote.getId() !=null && quote.getId().equals(id)) {
                return true;// exist on table
            }
        }
        return false;
    }

    @Override
    public Iterable<TraderEntity> findAll() {
        String selectSql = "SELECT * FROM " + this.getTableName();
        return jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(TraderEntity.class));
    }

    @Override
    public Iterable<TraderEntity> findAllById(Iterable<Integer> iterable) {
        String selectSql = "SELECT * FROM " + this.getTableName();
        List<TraderEntity> all = new ArrayList<>(jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(TraderEntity.class)));
        List<TraderEntity> filter = new ArrayList<>();
        for (Integer id: iterable) {
            all.stream().filter(traderEntity -> traderEntity.getId().equals(id)).forEach(filter::add);
        }
        return filter;
    }

    @Override
    public long count() {
        Iterable<TraderEntity> all = this.findAll();
        return (all instanceof Collection) ? ((Collection<TraderEntity>) all).size() : Math.toIntExact(StreamSupport.stream(all.spliterator(), false).count());
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null");
        }
        String deleteSql = "DELETE FROM " + this.getTableName() + " WHERE " + this.getIdColumnName()  + " =?";
        if (jdbcTemplate.update(deleteSql, id) != 1) {
            throw new IncorrectResultSizeDataAccessException(1);
        }
    }

    @Override
    public void delete(TraderEntity traderEntity) {
        deleteById(traderEntity.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends TraderEntity> iterable) {
        iterable.forEach(q->deleteById(q.getId()));
    }

    @Override
    public void deleteAll() {
        Iterable<TraderEntity> all = findAll();
        all.forEach(q->deleteById(q.getId()));
    }
}

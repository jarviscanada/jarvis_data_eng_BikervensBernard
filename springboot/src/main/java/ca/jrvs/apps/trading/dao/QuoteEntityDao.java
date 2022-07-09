package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.QuoteEntity;
import ca.jrvs.apps.trading.model.helper.MarketDataConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Repository
public class QuoteEntityDao implements CrudRepository<QuoteEntity, String> {

    public static final String TABLE_NAME="quote";
    public static final String ID_COLUMN_NAME = "ticker";
    public static final Logger logger = LoggerFactory.getLogger(QuoteEntityDao.class);
    private final HttpClientConnectionManager httpClientConnectionManager;
    private final MarketDataConfig marketDataConfig;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public QuoteEntityDao(DataSource dataSource, PoolingHttpClientConnectionManager cm, MarketDataConfig marketDataConfig) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
        this.httpClientConnectionManager = cm;
        this.marketDataConfig = marketDataConfig;
    }


    /**
     * if the quote exits in db this methode will try to modify the row
     * else it does not exist in db therefore it need to be added*/
    @Override
    public <S extends QuoteEntity> S save(S quote) {
       if(existsById(quote.getId())) {
           int updatedRowNo = this.updateOne(quote);
           if (updatedRowNo != 1) {
               throw new DataRetrievalFailureException("unable to update quote");
           }
       } else {
           this.addOne(quote);
       }
       return quote;
    }

    private <S extends QuoteEntity> int updateOne(QuoteEntity quote) {
        String update_sql = "UPDATE quote SET last_price=?, bid_price=?, "
                + "bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
        return jdbcTemplate.update(update_sql, makeUpdateValues( quote));
    }

    private Object[] makeUpdateValues(QuoteEntity quote) {
        return new Object[]{
                quote.getLastPrice(),
                quote.getBidPrice(),
                quote.getBidSize(),
                quote.getAskPrice(),
                quote.getAskSize(),
                quote.getId()
        };
    }

    private <S extends QuoteEntity> int addOne(S quote) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
        int row = simpleJdbcInsert.execute(parameterSource);
        if (row != 1) {
            throw new IncorrectResultSizeDataAccessException("Failed to insert",1,row);
        }
        return row;
    }

    @Override
    public <S extends QuoteEntity> Iterable<S> saveAll(Iterable<S> quotes) {
        String updateSql = "UPDATE quote SET last_price=?, bid_price=?, bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
        List<Object[]> batch = new ArrayList<>();
        quotes.forEach(quoteEntity -> {
            if (!existsById(quoteEntity.getTicker())) {
                throw new DataRetrievalFailureException("Ticker not found:" + quoteEntity.getTicker());
            }
            Object[] values = new Object[]{quoteEntity.getLastPrice(), quoteEntity.getBidPrice(), quoteEntity.getBidSize(),
                    quoteEntity.getAskPrice(), quoteEntity.getAskSize(), quoteEntity.getTicker()};
            batch.add(values);
        });
        int[] rows = jdbcTemplate.batchUpdate(updateSql, batch);
        int totalRow = Arrays.stream(rows).sum();

        int size = quotes instanceof Collection?
                ((Collection<QuoteEntity>) quotes).size() :
                Math.toIntExact(StreamSupport.stream(quotes.spliterator(), false).count());

        if (totalRow != size) {
            throw new IncorrectResultSizeDataAccessException("Number of rows ", size, totalRow);
        }
        return quotes;
    }

    @Override
    public Optional<QuoteEntity> findById(String id) {
        String selectSql = "SELECT * FROM " +TABLE_NAME+" WHERE ticker=?";
        List<QuoteEntity> res = jdbcTemplate.query(selectSql, new Object[]{id}, BeanPropertyRowMapper.newInstance(QuoteEntity.class) );
        return Optional.of( res.get(0) );
    }

    @Override
    public boolean existsById(String s) {
        // the ticker exist on iex
        if (new MarketDataDao(this.httpClientConnectionManager,this.marketDataConfig).findById(s).isPresent()) {
            String selectSql = "SELECT * FROM " + TABLE_NAME;
            List<QuoteEntity> quotes =  jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(QuoteEntity.class));
            for (QuoteEntity quote : quotes) {
                if (quote.getId().equals(s.toUpperCase())) {
                    // the ticker exist on quote table
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterable<QuoteEntity> findAll() {
        String selectSql = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(QuoteEntity.class));
    }

    @Override
    public Iterable<QuoteEntity> findAllById(Iterable<String> iterable) {
       String selectSql = "SELECT * FROM " + TABLE_NAME;
       List<QuoteEntity> all = new ArrayList<>(jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(QuoteEntity.class)));
       List<QuoteEntity> filter = new ArrayList<>();
       for (String id: iterable) {
           all.stream().filter(quoteEntity -> quoteEntity.getId().equals(id)).forEach(filter::add);
       }
       return filter;
    }

    @Override
    public long count() {
        Iterable<QuoteEntity> all = this.findAll();
        return (all instanceof Collection) ? ((Collection<QuoteEntity>) all).size() : Math.toIntExact(StreamSupport.stream(all.spliterator(), false).count());
    }

    @Override
    public void deleteById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null");
        }
        String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME  + " =?";
        if (jdbcTemplate.update(deleteSql, id) != 1) {
            throw new IncorrectResultSizeDataAccessException(1);
        }
    }

    @Override
    public void delete(QuoteEntity quote) {
        deleteById(quote.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends QuoteEntity> iterable) {
        iterable.forEach(q->deleteById(q.getId()));
    }

    @Override
    public void deleteAll() {
        Iterable<QuoteEntity> all = findAll();
        all.forEach(q->deleteById(q.getId()));
    }
}

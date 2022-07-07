package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class QuoteDao implements CrudRepository<Quote, String> {

    public static final String TABLE_NAME="quote";
    public static final String ID_COLUMN_NAME = "ticker";
    public static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public QuoteDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
    }


    @Override
    public <S extends Quote> S save(S quote) {
       if(existsById((String) quote.getId())) {
           
           int updatedRowNo = this.updateOne(quote);
           if (updatedRowNo != 1) {
               throw new DataRetrievalFailureException("unable to update quote");
           } else {
               this.addOne(quote);
           }
       }    return quote;
    }

    private <S extends Quote> int updateOne(S quote) {
        String update_sql = "UPDATE quote SET " +
                "ticker=?, " +
                "last_price=?, " +
                "iex_ask_price=?, " +
                "iex_ask_size=?, " +
                "iex_bid_price=?, " +
                "iex_bid_size=?";
        return jdbcTemplate.update(update_sql,this.makeUpdateValues(quote));
    }

    private <S extends Quote> Object makeUpdateValues(S quote) {
        ArrayList args = new ArrayList();
        args.add(quote.getSymbol());
        args.add(quote.getPreviousClose());
        args.add(quote.getIexAskPrice());
        args.add(quote.getIexAskSize());
        args.add(quote.getIexBidPrice());
        args.add(quote.getIexBidSize());
        return args;
    }

    private <S extends Quote> void addOne(S quote) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
        int row = simpleJdbcInsert.execute(parameterSource);
        if (row != 1) {
            throw new IncorrectResultSizeDataAccessException("Failed to insert",1,row);
        }
    }

    @Override
    public <S extends Quote> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Quote> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<Quote> findAll() {
        return null;
    }

    @Override
    public Iterable<Quote> findAllById(Iterable<String> iterable) {
       throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Quote quote) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends Quote> iterable) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void deleteAll() {

    }
}

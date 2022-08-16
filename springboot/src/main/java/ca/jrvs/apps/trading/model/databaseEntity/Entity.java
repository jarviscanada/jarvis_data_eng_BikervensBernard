package ca.jrvs.apps.trading.model.databaseEntity;

public interface Entity<ID> {
    ID getId();

    /**
     * this is USUALLY not to be called by dev. it is to be used by BeanPropertyRowMapper. You would normally not want to set this value
     * it is auto generated and incremented on db (serial)
     * i.e. trader.setId(1) is permuted by will probably fail database validation since the field is serial*/
    void setId(ID id);
}

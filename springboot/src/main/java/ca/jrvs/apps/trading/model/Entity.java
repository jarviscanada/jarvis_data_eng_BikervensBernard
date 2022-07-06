package ca.jrvs.apps.trading.model;

public interface Entity<ID>{
    ID getId();
    void setId(ID id);
}

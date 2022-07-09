package ca.jrvs.apps.trading.model.databaseEntity;

public interface Entity<ID> {
    ID getId();
    void setId(ID id);
}

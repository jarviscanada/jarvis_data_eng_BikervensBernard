package ca.jrvs.apps.trading.model.databaseEntity;

public class PositionEntity implements Entity<Integer>{
    int id;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }
}

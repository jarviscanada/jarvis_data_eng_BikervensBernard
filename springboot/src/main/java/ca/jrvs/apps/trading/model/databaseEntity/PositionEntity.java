package ca.jrvs.apps.trading.model.databaseEntity;

import java.util.Objects;

public class PositionEntity implements Entity<Integer>{
    private int id;
    private int accountId;
    private String ticker;
    private int size;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositionEntity)) return false;
        PositionEntity that = (PositionEntity) o;
        return getId() == that.getId() && getAccountId() == that.getAccountId() && getSize() == that.getSize() && getTicker().equals(that.getTicker());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccountId(), getTicker(), getSize());
    }

    @Override
    public String toString() {
        return "PositionEntity{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", ticker='" + ticker + '\'' +
                ", size=" + size +
                '}';
    }
}

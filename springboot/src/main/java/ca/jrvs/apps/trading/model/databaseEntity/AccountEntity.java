package ca.jrvs.apps.trading.model.databaseEntity;

import java.util.Objects;

@org.springframework.stereotype.Component
public class AccountEntity implements Entity<Integer> {

    int id;
    int traderId;
    Double amount;

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountEntity)) return false;
        AccountEntity that = (AccountEntity) o;
        return getId() == that.getId() && getTraderId() == that.getTraderId() && Objects.equals(getAmount(), that.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTraderId(), getAmount());
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", traderId=" + traderId +
                ", amount=" + amount +
                '}';
    }
}

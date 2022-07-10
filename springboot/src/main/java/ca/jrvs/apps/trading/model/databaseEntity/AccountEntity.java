package ca.jrvs.apps.trading.model.databaseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "traderId", "amount"})
@org.springframework.stereotype.Component
public class AccountEntity implements Entity<Integer> {

    @JsonProperty("id")
    int id;
    @JsonProperty("traderId")
    int traderId;
    @JsonProperty("amount")
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

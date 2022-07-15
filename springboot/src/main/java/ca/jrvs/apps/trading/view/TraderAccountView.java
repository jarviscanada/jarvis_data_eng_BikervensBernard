package ca.jrvs.apps.trading.view;

import ca.jrvs.apps.trading.model.databaseEntity.AccountEntity;
import ca.jrvs.apps.trading.model.databaseEntity.TraderEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@org.springframework.stereotype.Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"trader", "account"})
public class TraderAccountView implements Serializable {

    @JsonProperty("trader")
    private TraderEntity trader;
    @JsonProperty("account")
    private AccountEntity account;

    @Autowired
    public TraderAccountView(TraderEntity trader, AccountEntity account) {
        this.trader = trader;
        this.account = account;
    }

    public TraderEntity getTrader() {
        return trader;
    }

    public void setTrader(TraderEntity trader) {
        this.trader = trader;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TraderAccountView.class.getName()).append('@').append(Double.toHexString(System.identityHashCode(this))).append('[');
        sb.append("trader");
        sb.append('=');
        sb.append(((this.trader == null)?"<null>":this.trader.toString()));
        sb.append(',');
        sb.append("account");
        sb.append('=');
        sb.append(((this.account == null)?"<null>":this.account.toString()));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}

package ca.jrvs.apps.trading.model.databaseEntity;

import java.util.Objects;

public class SecurityOrderEntity implements Entity<Integer>{
    private int id;
    private int accountId;
    private String status;
    private String ticker;
    private int size;
    private Double price;
    private String notes;

    public static final String FILLED = "FILLED";

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SecurityOrderEntity)) return false;
        SecurityOrderEntity that = (SecurityOrderEntity) o;
        return getId() == that.getId() && getAccountId() == that.getAccountId() && getSize() == that.getSize() && Objects.equals(getStatus(), that.getStatus()) && getTicker().equals(that.getTicker()) && getPrice().equals(that.getPrice()) && Objects.equals(getNotes(), that.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccountId(), getStatus(), getTicker(), getSize(), getPrice(), getNotes());
    }

    @Override
    public String toString() {
        return "SecurityOrderEntity{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", status='" + status + '\'' +
                ", ticker='" + ticker + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", notes='" + notes + '\'' +
                '}';
    }
}

package ca.jrvs.apps.trading.model.databaseEntity;

import java.sql.Date;
import java.util.Objects;

public class TraderEntity implements Entity<Integer> {

    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String country ;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Integer getId() {
        return id;
    }

    /**
     * this is not to be called by dev. it is to be used by BeanPropertyRowMapper. You would normally not want to set this value
     * it is auto generated and incremented on db (serial)
     * i.e. trader.setId(1) is permuted by will probably fail database validation since the field is serial*/
    @Override
    public void setId(Integer integer) {
        id = integer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TraderEntity)) return false;
        TraderEntity that = (TraderEntity) o;
        return getId() == that.getId() && getFirstName().equals(that.getFirstName()) && getLastName().equals(that.getLastName()) && Objects.equals(getDob(), that.getDob()) && Objects.equals(getCountry(), that.getCountry()) && getEmail().equals(that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getDob(), getCountry(), getEmail());
    }

    @Override
    public String toString() {
        return "TraderEntity{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", dob=" + dob +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

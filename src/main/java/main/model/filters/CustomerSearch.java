package main.model.filters;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class CustomerSearch {

    private int customer_id;

    private String first_name;

    private String last_name;

    private String email;

    private boolean activeBool;

    private Date create_date;

    private Timestamp last_update;

    private int active;

    public CustomerSearch() {
    }

    public CustomerSearch(int customer_id, String first_name, String last_name, String email, boolean activeBool, Date create_date, Timestamp last_update, int active) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.activeBool = activeBool;
        this.create_date = create_date;
        this.last_update = last_update;
        this.active = active;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActiveBool() {
        return activeBool;
    }

    public void setActiveBool(boolean activeBool) {
        this.activeBool = activeBool;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSearch that = (CustomerSearch) o;
        return customer_id == that.customer_id &&
                activeBool == that.activeBool &&
                active == that.active &&
                Objects.equals(first_name, that.first_name) &&
                Objects.equals(last_name, that.last_name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(create_date, that.create_date) &&
                Objects.equals(last_update, that.last_update);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_id, first_name, last_name, email, activeBool, create_date, last_update, active);
    }
}

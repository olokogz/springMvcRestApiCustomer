package main.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rental_id;

    private Timestamp rental_date;

    private Timestamp return_date;

    private Timestamp last_update;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    public Rental() {
    }

    public Rental(int rental_id, Timestamp rental_date, Timestamp return_date, Timestamp last_update) {
        this.rental_id = rental_id;
        this.rental_date = rental_date;
        this.return_date = return_date;
        this.last_update = last_update;
    }

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public Timestamp getRental_date() {
        return rental_date;
    }

    public void setRental_date(Timestamp rental_date) {
        this.rental_date = rental_date;
    }

    public Timestamp getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Timestamp return_date) {
        this.return_date = return_date;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return rental_id == rental.rental_id &&
                Objects.equals(rental_date, rental.rental_date) &&
                Objects.equals(return_date, rental.return_date) &&
                Objects.equals(last_update, rental.last_update);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rental_id, rental_date, return_date, last_update);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rental_id=" + rental_id +
                ", rental_date=" + rental_date +
                ", return_date=" + return_date +
                ", last_update=" + last_update +
                '}';
    }
}

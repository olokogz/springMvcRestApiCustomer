package main.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staff_id;

    private String first_name;

    private String last_name;

    private String email;

    private boolean active;

    private String username;

    private String password;

    private Timestamp last_update;

    @JsonIgnore
    @OneToOne(mappedBy = "staff",
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER,
                    cascade = {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.PERSIST,
                            CascadeType.REFRESH
                    })
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(fetch = FetchType.EAGER,
    cascade = {
        CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.PERSIST,
                CascadeType.REFRESH
    })
    @JoinColumn(name = "staff_id")
    private Set<Rental> rentals;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "staff_id")
    private Set<Payment> payments;


    public Staff() {
    }

    public Staff(int staff_id, String first_name, String last_name, String email, boolean active, String username, String password, Timestamp last_update) {
        this.staff_id = staff_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.active = active;
        this.username = username;
        this.password = password;
        this.last_update = last_update;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return staff_id == staff.staff_id &&
                active == staff.active &&
                Objects.equals(first_name, staff.first_name) &&
                Objects.equals(last_name, staff.last_name) &&
                Objects.equals(email, staff.email) &&
                Objects.equals(username, staff.username) &&
                Objects.equals(password, staff.password) &&
                Objects.equals(last_update, staff.last_update) &&
                Objects.equals(store, staff.store) &&
                Objects.equals(address, staff.address); //&&
                //Objects.equals(rentals, staff.rentals); //&&
                //Objects.equals(payments, staff.payments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staff_id, first_name, last_name, email, active, username, password, last_update, store, address);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staff_id=" + staff_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", last_update=" + last_update +
                '}';
    }
}

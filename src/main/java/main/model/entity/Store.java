package main.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int store_id;

    private int manager_staff_id;

    private Timestamp last_update;

    @JsonIgnore
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    },
            fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "store_id")
    private Staff staff;

    public Store() {
    }

    public Store(int store_id, int manager_staff_id, int address_id, Timestamp last_update) {
        this.store_id = store_id;
        this.manager_staff_id = manager_staff_id;
        this.last_update = last_update;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getManager_staff_id() {
        return manager_staff_id;
    }

    public void setManager_staff_id(int manager_sta_id) {
        this.manager_staff_id = manager_sta_id;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /*public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }*/
}

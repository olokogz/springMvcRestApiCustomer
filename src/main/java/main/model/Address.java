package main.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int address_id;

    private String address;

    private String address2;

    private String district;

    private String postal_code;

    private String phone;

    private Timestamp last_update;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "address",
            cascade =
            {CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH})
    private List<Customer> customer;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "city_id")
    private City city;

    public Address() {
    }

    public Address(String address, String address2, String district, String postal_code, String phone, Timestamp last_update) {
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.postal_code = postal_code;
        this.phone = phone;
        this.last_update = last_update;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    public void add(Customer customer)
    {
        if(this.customer == null)
        {
            this.customer = new ArrayList<>();
        }

        this.customer.add(customer);
        customer.setAddress(this);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address: "+address+" "+address2+" Post Code: "+postal_code+" Phone: "+phone+" city: ";
    }
}

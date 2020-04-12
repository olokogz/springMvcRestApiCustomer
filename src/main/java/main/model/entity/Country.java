package main.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int country_id;

    private String country;

    private Timestamp last_update;

    @JsonIgnore
    @OneToOne(mappedBy = "country",
    fetch = FetchType.LAZY,
    cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private City city;

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

   /* public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }*/

    @Override
    public String toString() {
        return "Country{" +
                "country_id=" + country_id +
                ", country='" + country + '\'' +
                ", last_update=" + last_update +
                '}';
    }
}

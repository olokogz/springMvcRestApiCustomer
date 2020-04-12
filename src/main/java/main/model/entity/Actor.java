package main.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="actor")
public class Actor {

    @Id
    private int actor_id;

    private String first_name;

    private String last_name;

    private Timestamp last_update;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,
    cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name="film_actor",
            joinColumns = @JoinColumn(name="actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private List<Film> filmList;

    public Actor() {
    }

    public Actor(String first_name, String last_name, Timestamp last_update) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.last_update = last_update;
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
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

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public List<Film> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
    }

    public void addFilm(Film film)
    {
        if(filmList == null)
        {
            filmList = new ArrayList<>();
        }
        filmList.add(film);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actor_id=" + actor_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", last_update=" + last_update +
                '}';
    }
}

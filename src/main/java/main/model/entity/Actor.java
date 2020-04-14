package main.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="actor")
public class Actor {

    @Id
    private int actor_id;

    private String first_name;

    private String last_name;

    private Timestamp last_update;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return actor_id == actor.actor_id &&
                Objects.equals(first_name, actor.first_name) &&
                Objects.equals(last_name, actor.last_name) &&
                Objects.equals(last_update, actor.last_update);// &&
                //Objects.equals(filmList, actor.filmList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actor_id, first_name, last_name, last_update);
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

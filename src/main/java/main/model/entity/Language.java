package main.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int language_id;

    private String name;

    private Timestamp last_update;

    @JsonIgnore
    @OneToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    },
            fetch = FetchType.EAGER,
    mappedBy = "language")
    private List<Film> film;

    public Language() {
    }

    public Language(int language_id, String name, Timestamp last_update) {
        this.language_id = language_id;
        this.name = name;
        this.last_update = last_update;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public List<Film> getFilm() {
        return film;
    }

    public void setFilm(List<Film> film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "Language{" +
                "language_id=" + language_id +
                ", name='" + name + '\'' +
                ", last_update=" + last_update +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return language_id == language.language_id &&
                Objects.equals(name, language.name) &&
                Objects.equals(last_update, language.last_update);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language_id, name, last_update);
    }
}

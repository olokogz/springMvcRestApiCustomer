package main.model.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import main.model.FilmRental;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
)
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int film_id;

    private String title;

    private String description;

    private int release_year;

    private int rental_duration;

    private double rental_rate;

    private double replacement_cost;

    private String rating;

    private Timestamp last_update;

    @Transient
    private FilmRental isRented;

    private String inventory_id;

    @Type(type="string-array")
    @Column(columnDefinition = "text[]")
    private String[] special_features;

    private String fulltext;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinTable(
            name="film_actor",
            joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Actor> actorList;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    },
    fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name="film_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Category> category;

    public Film() {
    }

    public Film(int film_id, String title, String description, int release_year, int rental_duration, double rental_rate, double replacement_cost, String rating, Timestamp last_update, FilmRental isRented, String inventory_id, String[] special_features, String fulltext) {
        this.film_id = film_id;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.replacement_cost = replacement_cost;
        this.rating = rating;
        this.last_update = last_update;
        this.isRented = isRented;
        this.inventory_id = inventory_id;
        this.special_features = special_features;
        this.fulltext = fulltext;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public int getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(int renal_duration) {
        this.rental_duration = renal_duration;
    }

    public double getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(int rental_rate) {
        this.rental_rate = rental_rate;
    }

    public double getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(double replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public String[] getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String[] special_features) {
        this.special_features = special_features;
    }

    public void setRental_rate(double rental_rate) {
        this.rental_rate = rental_rate;
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public void addActor(Actor actor)
    {
        if (actorList == null)
        {
            actorList = new ArrayList<>();
        }

        actorList.add(actor);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public FilmRental getIsRented() {
        if(getInventory_id()!=null)
        {
            return FilmRental.RENTED;
        }
        return FilmRental.NOT_RENTED;
    }

    public void setIsRented(FilmRental isRented) {
        this.isRented = isRented;
    }

    public String getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
    }

    @Override
    public String toString() {
        return "Film{" +
                "film_id=" + film_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", release_year=" + release_year +
                ", rental_duration=" + rental_duration +
                ", rental_rate=" + rental_rate +
                ", replacement_cost=" + replacement_cost +
                ", rating='" + rating + '\'' +
                ", last_update=" + last_update +
                ", special_features=" + Arrays.toString(special_features) +
                ", fulltext='" + fulltext + '\'' +
                '}';
    }
}

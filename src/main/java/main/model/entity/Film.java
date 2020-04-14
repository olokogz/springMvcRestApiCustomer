package main.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.search.PostgreSQLTSVectorType;
import main.model.FilmRental;
import main.model.MpaaRating;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Entity
@TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
)
@TypeDef(
        name="mpaa-rating",
        typeClass = PostgreSQLEnumType.class
)
@TypeDef(
        name="tsvector",
        typeClass = PostgreSQLTSVectorType.class
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

    private int length;

    @Enumerated(EnumType.STRING)
    @Type(type = "mpaa-rating")
    private MpaaRating rating;

    private Timestamp last_update;

    @JsonIgnore
    private Integer inventory_id;

    @Transient
    private FilmRental isRented;

    @Transient
    private int language_id;

    @Transient
    private int[] actor_id;

    @Transient
    private int[] category_id;

    @Type(type="string-array")
    @Column(columnDefinition = "text[]")
    private String[] special_features;

    @Type(type = "tsvector")
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
            joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Category> category;

    public Film() {
    }

    public Film(int film_id, String title, String description, int release_year, int rental_duration, int length, double replacement_cost, Timestamp last_update, Integer inventory_id, FilmRental isRented, int language_id, int[] actor_id, int[] category_id, String[] special_features) {
        this.film_id = film_id;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.replacement_cost = replacement_cost;
        this.last_update = last_update;
        this.inventory_id = inventory_id;
        this.isRented = isRented;
        this.language_id = language_id;
        this.actor_id = actor_id;
        this.category_id = category_id;
        this.special_features = special_features;
        this.length = length;
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

    public double getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(double replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public MpaaRating getRating() {
        return rating;
    }

    public void setRating(MpaaRating rating) {
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

    public void addCategory(Category category)
    {
        if(this.category == null)
        {
            this.category = new ArrayList<>();
        }
        this.category.add(category);
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

    public Integer getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(Integer inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public int[] getActor_id() {
        return actor_id;
    }

    public void setActor_id(int[] actor_id) {
        this.actor_id = actor_id;
    }

    public int[] getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int[] category_id) {
        this.category_id = category_id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return film_id == film.film_id &&
                release_year == film.release_year &&
                rental_duration == film.rental_duration &&
                Double.compare(film.rental_rate, rental_rate) == 0 &&
                Double.compare(film.replacement_cost, replacement_cost) == 0 &&
                length == film.length &&
                language_id == film.language_id &&
                Objects.equals(title, film.title) &&
                Objects.equals(description, film.description) &&
                rating == film.rating &&
                Objects.equals(last_update, film.last_update) &&
                Objects.equals(inventory_id, film.inventory_id) &&
                isRented == film.isRented &&
                Arrays.equals(actor_id, film.actor_id) &&
                Arrays.equals(category_id, film.category_id) &&
                Arrays.equals(special_features, film.special_features) &&
                Objects.equals(fulltext, film.fulltext) &&
                Objects.equals(actorList, film.actorList) &&
                Objects.equals(language, film.language) &&
                Objects.equals(category, film.category);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(film_id, title, description, release_year, rental_duration, rental_rate, replacement_cost, length, rating, last_update, inventory_id, isRented, language_id, fulltext, actorList, language, category);
        result = 31 * result + Arrays.hashCode(actor_id);
        result = 31 * result + Arrays.hashCode(category_id);
        result = 31 * result + Arrays.hashCode(special_features);
        return result;
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
                ", length=" + length +
                ", rating=" + rating +
                ", last_update=" + last_update +
                ", inventory_id=" + inventory_id +
                ", isRented=" + isRented +
                ", language_id=" + language_id +
                ", actor_id=" + Arrays.toString(actor_id) +
                ", category_id=" + Arrays.toString(category_id) +
                ", special_features=" + Arrays.toString(special_features) +
                ", fulltext='" + fulltext + '\'' +
                '}';
    }
}

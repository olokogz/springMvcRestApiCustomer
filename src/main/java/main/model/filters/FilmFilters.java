package main.model.filters;

import main.model.FilmRental;

import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.Objects;

public class FilmFilters {
    private int film_id;

    private String title;

    private String description;

    private int release_year;

    private int rental_duration;

    private double rental_rate;

    private double replacement_cost;

    private String rating;

    private Timestamp last_update;

    private String inventory_id;

    public FilmFilters() {
    }

    public FilmFilters(int film_id, String title, String description, int release_year, int rental_duration, double rental_rate, double replacement_cost, String rating, Timestamp last_update, String inventory_id) {
        this.film_id = film_id;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.replacement_cost = replacement_cost;
        this.rating = rating;
        this.last_update = last_update;
        this.inventory_id = inventory_id;
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

    public void setRental_duration(int rental_duration) {
        this.rental_duration = rental_duration;
    }

    public double getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(double rental_rate) {
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

    public String getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmFilters that = (FilmFilters) o;
        return film_id == that.film_id &&
                release_year == that.release_year &&
                rental_duration == that.rental_duration &&
                Double.compare(that.rental_rate, rental_rate) == 0 &&
                Double.compare(that.replacement_cost, replacement_cost) == 0 &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(last_update, that.last_update) &&
                Objects.equals(inventory_id, that.inventory_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film_id, title, description, release_year, rental_duration, rental_rate, replacement_cost, rating, last_update, inventory_id);
    }

    @Override
    public String toString() {
        return "FilmFilters{" +
                "film_id=" + film_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", release_year=" + release_year +
                ", rental_duration=" + rental_duration +
                ", rental_rate=" + rental_rate +
                ", replacement_cost=" + replacement_cost +
                ", rating='" + rating + '\'' +
                ", last_update=" + last_update +
                ", inventory_id='" + inventory_id + '\'' +
                '}';
    }
}

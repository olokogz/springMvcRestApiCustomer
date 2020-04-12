package main.model.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventory_id;

    private Timestamp last_update;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name="inventory_id")
    private Set<Film> filmSet;

    public Inventory() {
    }

    public Inventory(int inventory_id, Timestamp last_update) {
        this.inventory_id = inventory_id;
        this.last_update = last_update;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public Set<Film> getFilmSet() {
        return filmSet;
    }

    public void setFilmSet(Set<Film> filmSet) {
        this.filmSet = filmSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return inventory_id == inventory.inventory_id &&
                Objects.equals(last_update, inventory.last_update) &&
                Objects.equals(filmSet, inventory.filmSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventory_id, last_update, filmSet);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventory_id=" + inventory_id +
                ", last_update=" + last_update +
                '}';
    }
}

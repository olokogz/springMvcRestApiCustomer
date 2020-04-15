package main.dao;

import main.model.entity.Film;

import java.util.List;
import java.util.Map;

public interface FilmDAO extends BasicOperationDAO{

    public List<Film> getNotRentedFilms(List<Film> filmList);

    public List<Film> getRentedFilms(List<Film> filmList);

    public List<Film> filterFilms(Map<String,Object> filterMap);

    public void deleteFilm(int film_id);

    public void addFilm(Film film);

    public void rentFilm(int film_id, int inventory_id);

    public void backFilm(int film_id, int inventory_id);
}

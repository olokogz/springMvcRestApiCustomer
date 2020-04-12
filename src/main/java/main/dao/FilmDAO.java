package main.dao;

import main.model.entity.Film;

import java.util.List;
import java.util.Map;

public interface FilmDAO {

    public List<Film> getFilms();

    public List<Film> getNotRentedFilms(List<Film> filmList);

    public List<Film> getRentedFilms(List<Film> filmList);

    public List<Film> filterFilms(Map<String,Object> filterMap);
}

package main.controller;


import main.dao.FilmDAO;
import main.model.entity.Film;
import main.model.filters.FilmFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Map;

import static main.utils.Utils.*;

@RestController
@RequestMapping("/api")
public class FilmController {

    @Autowired
    FilmDAO filmDAO;


    @GetMapping("/film")
    public ResponseEntity<?> getFilms()
    {
        return ResponseEntity.ok().body(filmDAO.getFilms());
    }

    @GetMapping("/film/rented")
    public ResponseEntity<?>getRentedFilms(){return ResponseEntity.ok(filmDAO.getRentedFilms(filmDAO.getFilms()));}

    @GetMapping("/film/notrented")
    public ResponseEntity<?>getNotRentedFilms(){return ResponseEntity.ok(filmDAO.getNotRentedFilms(filmDAO.getFilms()));}

    @GetMapping("/film/filter")
    public ResponseEntity<?>filterFilms(@RequestBody FilmFilters filmFilters){
        return ResponseEntity.ok(filmDAO.filterFilms(findUpdatedFields(filmFilters)));
    }

    @PostMapping("/film")
    public ResponseEntity<?> addFilm(@RequestBody Film film)
    {
        filmDAO.addFilm(film);
        return ResponseEntity.ok("Film added");
    }
    @GetMapping("/film/{filmId}")
    public ResponseEntity<?> getFilmById(@PathVariable int filmId)
    {
        return ResponseEntity.ok(filmDAO.getFilmById(filmId));
    }
}

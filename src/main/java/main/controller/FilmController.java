package main.controller;


import main.dao.FilmDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

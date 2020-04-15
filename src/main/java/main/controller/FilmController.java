package main.controller;


import main.controller.errors.NotFoundException;
import main.dao.ActorDAO;
import main.dao.FilmDAO;
import main.dao.LanguageDAO;
import main.model.entity.Actor;
import main.model.entity.Category;
import main.model.entity.Film;
import main.model.entity.Language;
import main.model.filters.FilmFilters;
import main.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static main.utils.Utils.*;

@RestController
@RequestMapping("/api")
public class FilmController {

    @Autowired
    FilmDAO filmDAO;

    @Autowired
    LanguageDAO languageDAO;

    @Autowired
    ActorDAO actorDAO;

    @GetMapping("/film")
    public ResponseEntity<?> getFilms()
    {
        return ResponseEntity.ok().body(filmDAO.getObjectList());
    }

    @GetMapping("/film/rented")
    public ResponseEntity<?>getRentedFilms(){return ResponseEntity.ok(filmDAO.getRentedFilms((List<Film>)filmDAO.getObjectList()));}

    @GetMapping("/film/notrented")
    public ResponseEntity<?>getNotRentedFilms(){return ResponseEntity.ok(filmDAO.getNotRentedFilms((List<Film>)filmDAO.getObjectList()));}

    @GetMapping("/film/filter")
    public ResponseEntity<?>filterFilms(@RequestBody FilmFilters filmFilters){
        return ResponseEntity.ok(filmDAO.filterFilms(findUpdatedFields(filmFilters)));
    }

    @PostMapping("/film")
    public ResponseEntity<?> addFilm(@RequestBody Film film)
    {
        filmDAO.addFilm(film);
        return ResponseEntity.ok("Film added id: "+film.getFilm_id());
    }
    @GetMapping("/film/{filmId}")
    public ResponseEntity<?> getFilmById(@PathVariable int filmId)
    {
        return ResponseEntity.ok(filmDAO.getObjectById(filmId));
    }

    @PutMapping("/film")
    public ResponseEntity<?> updateFilm(@RequestBody Film film) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Film oldFilm = (Film)filmDAO.getObjectById(film.getFilm_id());
        if(oldFilm == null)
        {
            throw new NotFoundException("Film not found id: "+film.getFilm_id());
        }

        Film filmTemp = (Film)Utils.prepareUpdateObject(Utils.findUpdatedFields(film),film,oldFilm);

        if(filmTemp.getLanguage_id()!=0)
        {
            filmTemp.setLanguage((Language)languageDAO.getObjectById(filmTemp.getLanguage_id()));
        }
        else if(filmTemp.getActor_id()!=null)
        {
            for(int i=0;i<filmTemp.getActor_id().length;i++)
            {
                Actor actorTemp = (Actor)actorDAO.getObjectById(filmTemp.getActor_id()[i]);
                if(!filmTemp.getActorList().contains(actorTemp))
                {
                    filmTemp.addActor(actorTemp);
                }
            }
        }
        else if(filmTemp.getCategory_id()!=null)
        {
            for(int i=0;i<filmTemp.getCategory_id().length;i++)
            {
                Category categoryTemp = (Category)actorDAO.getObjectById(filmTemp.getCategory_id()[i]);
                if(!filmTemp.getCategory().contains(categoryTemp))
                {
                    filmTemp.addCategory(categoryTemp);
                }
            }
        }
        else
        {
            filmTemp.setLanguage(oldFilm.getLanguage());
            filmTemp.setActorList(oldFilm.getActorList());
            filmTemp.setCategory(oldFilm.getCategory());
        }

        filmDAO.addFilm(filmTemp);
        return ResponseEntity.ok("Film updated id: "+filmTemp.getFilm_id());
    }
}

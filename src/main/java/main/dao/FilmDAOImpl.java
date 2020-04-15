package main.dao;


import main.model.FilmRental;
import main.model.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Transactional
public class FilmDAOImpl implements FilmDAO{

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List<Film> getNotRentedFilms(List<Film> filmList) {
        return filmList.stream().filter(x->x.getIsRented()==FilmRental.NOT_RENTED).collect(Collectors.toList());
    }

    @Override
    public List<Film> getRentedFilms(List<Film> filmList) {
        return filmList.stream().filter(x->x.getIsRented()==FilmRental.RENTED).collect(Collectors.toList());
    }

    @Override
    public List<Film> filterFilms(Map<String, Object> filterMap) {
       Session session = sessionFactory.getCurrentSession();
       StringBuilder filters = new StringBuilder();


       filterMap.entrySet().forEach(x->{
           if (filters.length() <= 1) {
               filters.append(" where ");
           }
               filters.append(x.getKey()+"=:"+x.getKey()+ " and ");
       });
        Query<Film> query = session.createQuery("from Film "+filters.substring(0,filters.lastIndexOf("and")).trim(),Film.class);

        filterMap.entrySet().forEach(x->
                query.setParameter(x.getKey(),x.getValue()));

        return query.getResultList();
    }

    @Override
    public void deleteFilm(int film_id) {
        Session session = sessionFactory.getCurrentSession();

        session.delete(session.get(Film.class,film_id));
    }

    @Override
    public void addFilm(Film film) {

        Session session = sessionFactory.getCurrentSession();
        if(film.getCategory_id()!=null && film.getActor_id()!=null && film.getLanguage_id()!=0) {
        film.setLanguage(session.get(Language.class,film.getLanguage_id()));


            for (int i = 0; i < film.getCategory_id().length; i++) {
                film.addCategory(session.get(Category.class, film.getCategory_id()[i]));
            }
            for (int i = 0; i < film.getActor_id().length; i++) {
                film.addActor(session.get(Actor.class, film.getActor_id()[i]));
            }
        }



        session.saveOrUpdate(film);
    }

    @Override
    public void rentFilm(int film_id, int inventory_id) {
        Session session = sessionFactory.getCurrentSession();

        Film film = session.get(Film.class,film_id);

        film.setInventory_id(inventory_id);

        session.update(film);
    }

    @Override
    public void backFilm(int film_id, int inventory_id) {
        Session session = sessionFactory.getCurrentSession();

        Film film = session.get(Film.class,film_id);

        film.setInventory_id(null);

        session.update(film);
    }

    @Override
    public Object getObjectById(int objectId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Film.class,objectId);
    }

    @Override
    public List<?> getObjectList() {
        Session session = sessionFactory.getCurrentSession();

        Query<Film> query = session.createQuery("from Film",Film.class);

        return query.getResultList();
    }
}

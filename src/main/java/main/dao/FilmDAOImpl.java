package main.dao;


import main.model.FilmRental;
import main.model.entity.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Transactional
public class FilmDAOImpl implements FilmDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Film> getFilms() {
        Session session = sessionFactory.getCurrentSession();

        Query<Film> query = session.createQuery("from Film",Film.class);

        return query.getResultList();
    }

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
}

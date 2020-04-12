package main.dao;


import main.model.entity.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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
}

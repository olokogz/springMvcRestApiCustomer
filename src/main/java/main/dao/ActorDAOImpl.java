package main.dao;

import main.model.entity.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ActorDAOImpl implements ActorDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Object getObjectById(int objectId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Actor.class,objectId);
    }

    @Override
    public List<?> getObjectList() {
        Session session = sessionFactory.getCurrentSession();

        Query<Actor> query = session.createQuery("from Actor",Actor.class);

        return query.getResultList();
    }
}

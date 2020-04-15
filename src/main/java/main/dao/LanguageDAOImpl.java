package main.dao;

import main.model.entity.Actor;
import main.model.entity.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LanguageDAOImpl implements LanguageDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Object getObjectById(int objectId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Language.class,objectId);
    }

    @Override
    public List<?> getObjectList() {
        Session session = sessionFactory.getCurrentSession();

        Query<Language> query = session.createQuery("from Language",Language.class);

        return query.getResultList();
    }
}

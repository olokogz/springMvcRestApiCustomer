package main.dao;

import main.model.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public Object getObjectById(int objectId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Category.class,objectId);
    }

    @Override
    public List<?> getObjectList() {
        Session session = sessionFactory.getCurrentSession();

        Query<Category> query = session.createQuery("from Category",Category.class);

        return query.getResultList();

    }
}

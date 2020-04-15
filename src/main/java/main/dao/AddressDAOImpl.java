package main.dao;

import main.model.entity.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;


@Component
@Transactional
public class AddressDAOImpl implements AddressDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Object getObjectById(int objectId) {
        Session session = sessionFactory.getCurrentSession();

        Query<Address> query = session.createQuery("from Address where address_id=:addressId");
        query.setParameter("addressId",objectId);

        return query.getSingleResult();
    }

    @Override
    public List<?> getObjectList() {
        Session session = sessionFactory.getCurrentSession();

        Query<Address> query = session.createQuery("from Address",Address.class);

        return query.getResultList();
    }
}

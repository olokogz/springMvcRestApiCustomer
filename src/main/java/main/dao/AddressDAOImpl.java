package main.dao;

import main.model.entity.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
@Transactional
public class AddressDAOImpl implements AddressDAO{

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public Address getAddressById(int addressId) {
        Session session = sessionFactory.getCurrentSession();

        Query<Address> query = session.createQuery("from Address where address_id=:addressId");
        query.setParameter("addressId",addressId);

        return query.getSingleResult();
    }
}

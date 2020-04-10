package main.dao;

import main.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery("From Customer",Customer.class);

        return query.getResultList();
    }

    @Override
    public Customer getCustomerById(int customerId) {

        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("from Customer where customer_id=:customerId");
        query.setParameter("customerId",customerId);

        return query.getSingleResult();
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
        return true;
    }

    @Override
    public void deleteCustomer(int customerId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getCustomerById(customerId));
    }
}

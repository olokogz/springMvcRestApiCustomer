package main.dao;

import main.model.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public boolean saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
        return true;
    }

    @Override
    public void deleteCustomer(int customerId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getObjectById(customerId));
    }

    @Override
    public List<Customer> filterCustomer(Map<String, Object> filterVal) {
        StringBuilder queryParams = new StringBuilder();
        Session session = sessionFactory.getCurrentSession();

        filterVal.entrySet().forEach(x->queryParams.append(x.getKey()).append("=:").append(x.getKey()).append(" and "));
        Query<Customer> query = session.createQuery("from Customer where "+queryParams.substring(0,queryParams.lastIndexOf("and")).trim());

        filterVal.entrySet().forEach(x->query.setParameter(x.getKey(),x.getValue()));
        System.out.println(query.getQueryString());
        return query.getResultList();

    }

    @Override
    public Object getObjectById(int objectId) {

        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("from Customer where customer_id=:customerId");
        query.setParameter("customerId",objectId);

        return query.getSingleResult();
    }

    @Override
    public List<?> getObjectList() {
        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery("From Customer",Customer.class);

        return query.getResultList();
    }
}

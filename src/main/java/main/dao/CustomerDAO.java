package main.dao;

import main.model.entity.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerDAO extends BasicOperationDAO {

    public boolean saveCustomer(Customer customer);

    public void deleteCustomer(int customerId);

    public List<Customer> filterCustomer(Map<String,Object> filterVal);
}

package main.dao;

import main.model.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers();

    public Customer getCustomerById(int customerId);

    public boolean saveCustomer(Customer customer);

    public void deleteCustomer(int customerId);
}

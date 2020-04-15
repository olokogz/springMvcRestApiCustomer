package main.controller;


import main.controller.errors.NotFoundException;
import main.dao.AddressDAO;
import main.dao.CustomerDAO;
import main.model.entity.Address;
import main.model.entity.Customer;
import main.model.filters.CustomerSearch;
import main.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class CustomerController extends Utils{

    private final Logger LOGGER =  Logger.getLogger(getClass().getName());

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    AddressDAO addressDAO;

    @GetMapping("/customer")
    public ResponseEntity<?> getCustomers()
    {
        return ResponseEntity.ok().body(customerDAO.getObjectList());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomerbyId(@PathVariable int customerId)
    {
        Object cust = customerDAO.getObjectById(customerId);

        if(cust == null)
        {
            throw new NotFoundException("Customer id not found "+customerId);
        }

        return ResponseEntity.ok().body((Customer)cust);
    }

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer)
    {

        Object address = addressDAO.getObjectById(customer.getAddress_id());
        if(address == null)
        {
            throw new NotFoundException("Bad address - not found"+customer.getAddress_id());
        }
        customer.setAddress((Address)address);
        customerDAO.saveCustomer(customer);
        return ResponseEntity.ok().body("User Added id: "+customer.getCustomer_id());
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int customerId)
    {
        Object cust = customerDAO.getObjectById(customerId);
        if(cust == null)
        {
            throw new NotFoundException("Customer doesnt exist!");
        }
        customerDAO.deleteCustomer(customerId);
        return ResponseEntity.ok("User deleted id: "+((Customer)cust).getCustomer_id());
    }

    @PutMapping("/customer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) throws NoSuchFieldException, IllegalAccessException, IntrospectionException, InvocationTargetException {

        Object updatingCustomer = customerDAO.getObjectById(customer.getCustomer_id());

        if(updatingCustomer == null)
        {
            throw new NotFoundException("Customer doesnt exist!");
        }

        Object customerTemp = Utils.prepareUpdateObject(Utils.findUpdatedFields(customer),customer,updatingCustomer);
        int addresId = ((Customer) customerTemp).getAddress_id();
        if(addresId!=0)
        {
            ((Customer) customerTemp).setAddress((Address)addressDAO.getObjectById(addresId));
            if(((Customer) customerTemp).getAddress() == null)
            {
                throw new NotFoundException("Address doesnt exist !");
            }
        }
        customerDAO.saveCustomer((Customer)customerTemp);

        return ResponseEntity.ok("User updated id: "+((Customer) customerTemp).getCustomer_id());
    }

    @GetMapping("/customer/filter")
    public ResponseEntity<?> filterCustomers(@RequestBody CustomerSearch customerSearch)
    {
        Map<String,Object> map = Utils.findUpdatedFields(customerSearch);

        return ResponseEntity.ok().body(customerDAO.filterCustomer(map));
    }

}

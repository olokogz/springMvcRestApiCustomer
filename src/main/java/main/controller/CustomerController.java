package main.controller;


import main.dao.AddressDAO;
import main.dao.CustomerDAO;
import main.model.Address;
import main.model.Customer;
import main.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
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
        customerDAO.getCustomers().forEach(x->LOGGER.severe(x.toString()));

        return ResponseEntity.ok().body(customerDAO.getCustomers());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomerbyId(@PathVariable int customerId)
    {
        customerDAO.getCustomerById(customerId);
        return ResponseEntity.ok().body(customerDAO.getCustomerById(customerId));
    }

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer)
    {

        Address address = addressDAO.getAddressById(customer.getAddress_id());
        customer.setAddress(address);
        customerDAO.saveCustomer(customer);
        return ResponseEntity.ok().body("User Added");
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int customerId)
    {
        customerDAO.deleteCustomer(customerId);
        return ResponseEntity.ok("User deleted");
    }

    @PutMapping("/customer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) throws NoSuchFieldException, IllegalAccessException, IntrospectionException, InvocationTargetException {

        LOGGER.severe("Addres: "+customer.getAddress());

        Customer updatingCustomer = customerDAO.getCustomerById(customer.getCustomer_id());

        Object customerTemp = Utils.prepareUpdateObject(Utils.findUpdatedFields(customer),customer,updatingCustomer);
        int addresId = ((Customer) customerTemp).getAddress_id();
        if(addresId!=0)
        {
            ((Customer) customerTemp).setAddress(addressDAO.getAddressById(addresId));
        }
        customerDAO.saveCustomer((Customer)customerTemp);

        return ResponseEntity.ok("User updated");
    }

}

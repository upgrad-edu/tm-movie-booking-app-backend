package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.daos.CustomerDAO;
import com.upgrad.mtb.daos.UserTypeDAO;
import com.upgrad.mtb.dto.CustomerDTO;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerUserNameExistsException;
import com.upgrad.mtb.exceptions.UserTypeDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    @Qualifier("customerDAO")
    CustomerDAO customerDAO;
    @Autowired
    UserTypeService userTypeService;

    @Override
    public Customer acceptCustomerDetails(CustomerDTO customerDTO) throws CustomerUserNameExistsException, UserTypeDetailsNotFoundException {
        if(customerDAO.findByUsername(customerDTO.getUsername()).isEmpty()){
            Customer newCustomer = new Customer();
            newCustomer.setFirstName(customerDTO.getFirstName());
            newCustomer.setLastName(customerDTO.getLastName());
            newCustomer.setUsername(customerDTO.getUsername());
            newCustomer.setPassword(customerDTO.getPassword());
            newCustomer.setPhoneNumbers(customerDTO.getPhoneNumbers());
            newCustomer.setBookings(customerDTO.getBookings());
            newCustomer.setDateOfBirth(customerDTO.getDateOfBirth());
            newCustomer.setUserType(userTypeService.getUserTypeDetails(customerDTO.getUserTypeId()));
            customerDAO.save(newCustomer);
            return newCustomer;
        }else{
            throw new CustomerUserNameExistsException("This username already exists please choose another : " + customerDTO.getUsername());
        }
    }

    @Override
    public Customer getCustomerDetails(int id) throws CustomerDetailsNotFoundException {
        Customer customer = customerDAO.findById(id).orElseThrow(
                ()->  new CustomerDetailsNotFoundException("Customer not found for id" + id));
        return customer;
    }

    @Override
    public Customer getCustomerDetailsByUsername(String username) throws CustomerDetailsNotFoundException {
        Customer customer = customerDAO.findByUsername(username).orElseThrow(
                ()->  new CustomerDetailsNotFoundException("Customer not found for username" + username));
        return customer;
    }

    @Override
    public Customer updateCustomerDetails(int initialCustomerId, CustomerDTO customer) throws CustomerDetailsNotFoundException, UserTypeDetailsNotFoundException {
        Customer initialCustomer = getCustomerDetails(initialCustomerId);
        System.out.println("Initial customer details : " + initialCustomer.toString());
        initialCustomer.setFirstName(customer.getFirstName());
        initialCustomer.setLastName(customer.getLastName());
        initialCustomer.setPassword(customer.getPassword());
        initialCustomer.setDateOfBirth(customer.getDateOfBirth());
        initialCustomer.setUserType(userTypeService.getUserTypeDetails(customer.getUserTypeId()));
        initialCustomer.setPhoneNumbers(customer.getPhoneNumbers());
        initialCustomer.setBookings(customer.getBookings());
        customerDAO.save(initialCustomer);
        System.out.println("New city details :" + getCustomerDetails(initialCustomerId).toString());
        return initialCustomer;

    }

    public Customer updateCustomerDetails(Customer customer){
        return customerDAO.save(customer);
    }

    @Override
    public boolean deleteCustomer(int id) throws CustomerDetailsNotFoundException {
        Customer customer = getCustomerDetails(id);
        customerDAO.delete(customer);
        return true;
    }

    @Override
    public List<Customer> getAllCustomerDetails() {
        return customerDAO.findAll();
    }
}

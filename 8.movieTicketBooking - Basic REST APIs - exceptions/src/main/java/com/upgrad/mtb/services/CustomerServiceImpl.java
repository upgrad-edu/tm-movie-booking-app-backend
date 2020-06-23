package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.daos.CustomerDAO;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerUserNameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    @Qualifier("customerDAO")
    CustomerDAO customerDAO;

    @Override
    public Customer acceptCustomerDetails(Customer customer) throws CustomerUserNameExistsException {
        if(!customerDAO.findByUsername(customer.getUsername()).isPresent()){
            return  customerDAO.save(customer);
        }else{
            throw new CustomerUserNameExistsException("This username already exists please choose another : " + customer.getUsername());
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
    public Customer updateCustomerDetails(int initialCustomerId, Customer customer) throws CustomerDetailsNotFoundException {
        Customer initialCustomer = getCustomerDetails(initialCustomerId);
        System.out.println("Initial customer details : " + initialCustomer.toString());
        initialCustomer.setFirstName(customer.getFirstName());
        initialCustomer.setLastName(customer.getLastName());
        initialCustomer.setPassword(customer.getPassword());
        initialCustomer.setDateOfBirth(customer.getDateOfBirth());
        initialCustomer.setUserType(customer.getUserType());
        initialCustomer.setPhoneNumbers(customer.getPhoneNumbers());
        customerDAO.save(initialCustomer);
        System.out.println("New city details :" + getCustomerDetails(initialCustomerId).toString());
        return initialCustomer;

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

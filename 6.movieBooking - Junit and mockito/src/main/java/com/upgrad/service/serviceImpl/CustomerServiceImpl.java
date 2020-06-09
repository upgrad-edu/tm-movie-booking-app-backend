package com.upgrad.service.serviceImpl;

import com.upgrad.DAO.CustomerDAO;
import com.upgrad.beans.Customer;
import com.upgrad.exceptions.CustomerNotFoundException;
import com.upgrad.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO  ;

    @Override
    public Customer createNewCustomer(Customer customer) {
        Customer customer1 = customerDAO.save(customer);
        return customer1;
    }

    @Override
    public Customer getCustomer(int customerId) throws CustomerNotFoundException {
        Customer customer = customerDAO.findById(customerId).orElseThrow(
                ()->  new CustomerNotFoundException("Customer not found for customerId" + customerId));
        return customer;
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException {
        Customer customer = getCustomer(customerId);
        customerDAO.delete(customer);
        return true;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerDAO.findAll();
    }
}

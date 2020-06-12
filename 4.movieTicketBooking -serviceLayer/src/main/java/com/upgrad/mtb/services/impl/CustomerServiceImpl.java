package com.upgrad.mtb.services.impl;

import com.upgrad.mtb.DAO.CustomerDAO;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerDAO customerDAO;

    @Override
    public Customer acceptCustomerDetails(Customer customer) {
        return  customerDAO.save(customer);

    }

    @Override
    public Customer getCustomerDetails(int customerId) throws CustomerDetailsNotFoundException {
        Customer customer = customerDAO.findById(customerId).orElseThrow(
                ()->  new CustomerDetailsNotFoundException("Customer not found for customerId" + customerId));
        return customer;
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerDetailsNotFoundException {
        Customer customer = getCustomerDetails(customerId);
        customerDAO.delete(customer);
        return true;
    }

    @Override
    public List<Customer> getAllCustomerDetails() {
        return customerDAO.findAll();
    }
}

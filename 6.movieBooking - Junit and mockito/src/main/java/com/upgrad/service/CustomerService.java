package com.upgrad.service;

import com.upgrad.beans.Customer;
import com.upgrad.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
     public Customer createNewCustomer(Customer customer);
     public Customer getCustomer(int customerId) throws CustomerNotFoundException;
     public boolean deleteCustomer(int customerId) throws CustomerNotFoundException;
     public List<Customer> getAllCustomer();
}

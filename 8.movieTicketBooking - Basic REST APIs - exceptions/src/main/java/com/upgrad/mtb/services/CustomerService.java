package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerUserNameExistsException;

import java.util.List;

public interface CustomerService {
     public Customer acceptCustomerDetails(Customer customer) throws CustomerUserNameExistsException;
     public Customer getCustomerDetails(int id) throws CustomerDetailsNotFoundException;
     public Customer getCustomerDetailsByUsername(String username) throws CustomerDetailsNotFoundException;
     public Customer updateCustomerDetails(int initialCustomerId, Customer customer) throws CustomerDetailsNotFoundException;
     public boolean deleteCustomer(int id) throws CustomerDetailsNotFoundException;
     public List<Customer> getAllCustomerDetails();
}

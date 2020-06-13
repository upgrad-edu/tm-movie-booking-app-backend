package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;

import java.util.List;

public interface CustomerService {
     public Customer acceptCustomerDetails(Customer customer);
     public Customer getCustomerDetails(int id) throws CustomerDetailsNotFoundException;
     public boolean deleteCustomer(int id) throws CustomerDetailsNotFoundException;
     public List<Customer> getAllCustomerDetails();
}

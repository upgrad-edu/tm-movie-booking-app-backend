package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.dto.CustomerDTO;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerUserNameExistsException;
import com.upgrad.mtb.exceptions.UserTypeDetailsNotFoundException;

import java.util.List;

public interface CustomerService {
     public Customer acceptCustomerDetails(CustomerDTO customer) throws CustomerUserNameExistsException, UserTypeDetailsNotFoundException;
     public Customer getCustomerDetails(int id) throws CustomerDetailsNotFoundException;
     public Customer getCustomerDetailsByUsername(String username) throws CustomerDetailsNotFoundException;
     public Customer updateCustomerDetails(int initialCustomerId, CustomerDTO customerDTO) throws CustomerDetailsNotFoundException, UserTypeDetailsNotFoundException;
     public Customer updateCustomerDetails(Customer customer);
     public boolean deleteCustomer(int id) throws CustomerDetailsNotFoundException;
     public List<Customer> getAllCustomerDetails();
}

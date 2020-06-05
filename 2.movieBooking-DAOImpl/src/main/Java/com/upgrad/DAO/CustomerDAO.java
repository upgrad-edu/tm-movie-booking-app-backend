package com.upgrad.DAO;

import com.upgrad.beans.Customer;

import java.util.List;

public interface CustomerDAO {
    public Customer createNewCustomer(Customer customer);
    public Customer getCustomer(int customerId);
    public boolean deleteCustomer(int customerId);
    public List<Customer> getAllCustomer();
}

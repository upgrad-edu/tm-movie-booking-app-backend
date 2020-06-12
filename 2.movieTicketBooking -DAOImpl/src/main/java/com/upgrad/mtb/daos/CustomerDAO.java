package com.upgrad.mtb.daos;
import com.upgrad.mtb.beans.Customer;

import java.util.List;

public interface CustomerDAO {
    public Customer acceptCustomerDetails1(Customer customer);

   // public Customer acceptCustomerDetails2(Customer customer);

    public Customer getCustomerDetails(int customerId);
    public boolean deleteCustomer(int customerId);
    public List<Customer> getAllCustomerDetails();
}

package com.upgrad.mtb.daos;
import com.upgrad.mtb.beans.Customer;

import java.util.List;

public interface CustomerDAO {
    public Customer acceptCustomerDetails(Customer customer);
    public Customer acceptCustomerDetailsTransactional(Customer customer);
    public Customer getCustomerDetails(int id);
    public boolean deleteCustomer(int id);
    public boolean deleteCustomerTransactional(int id);
    public List<Customer> getAllCustomerDetails();
}

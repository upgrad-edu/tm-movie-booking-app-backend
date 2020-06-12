package com.upgrad.mtb.DAO;
import com.upgrad.mtb.beans.Customer;

import java.util.List;

public interface CustomerDAO {
    public Customer acceptCustomerDetails(Customer customer);
    public Customer getCustomerDetails(int customerId);
    public boolean deleteCustomer(int customerId);
    public List<Customer> getAllCustomerDetails();
}

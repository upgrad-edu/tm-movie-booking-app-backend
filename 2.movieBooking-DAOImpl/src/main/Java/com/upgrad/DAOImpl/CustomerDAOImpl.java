package com.upgrad.DAOImpl;

import com.upgrad.DAO.CustomerDAO;
import com.upgrad.beans.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    List<Customer> customerList = new ArrayList<>();
    @Override
    public Customer createNewCustomer(Customer customer) {
        customerList.add(customer);
        return customerList.get(customerList.size()-1);
    }

    @Override
    public Customer getCustomer(int customerId) {
        for(Customer customer: customerList){
            if(customer.getCustomerId() == customerId)
                return customer;
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        for(Customer customer: customerList){
            if(customer.getCustomerId() == customerId)
                customerList.remove(customer);
                return true;
        }
        return false;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerList;
    }
}

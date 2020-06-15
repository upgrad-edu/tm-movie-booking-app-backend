package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.daos.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerDAO customerDAO;

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public Customer acceptCustomerDetails(Customer customer) {
        return customerDAO.acceptCustomerDetailsTransactional(customer);
    }
}

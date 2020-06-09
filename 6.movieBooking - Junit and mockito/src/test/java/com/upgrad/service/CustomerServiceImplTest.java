package com.upgrad.service;

import com.upgrad.DAO.CustomerDAO;
import com.upgrad.beans.Customer;
import com.upgrad.exceptions.CustomerNotFoundException;
import com.upgrad.util.PhoneNumber;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class CustomerServiceImplTest {
    Customer customer ;
    @MockBean
    CustomerDAO customerDAO;

    @Autowired
    CustomerService customerService;


    @Before
    void setUp() throws ParseException {
        customer = new Customer();
        customer.setUsername("ramKumar");
        customer.setPassword("password");
        customer.setFirstName("Ram");
        customer.setLastName("Kumar");
        customer.setCustomerId(1);
        customer.setPhoneNumber(new PhoneNumber("9009345678"));
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        customer.setDateOfBirth(myFormat.parse("01/06/1992"));
    }

    @Test
    void createNewCustomer() throws ParseException {
        when(customerDAO.save(customer)).thenReturn(new Customer(1,"Ram","Kumar","ramKumar","password",new SimpleDateFormat("dd/MM/yyyy").parse("01/06/1992"),1, new PhoneNumber("9009345678")));
        assertEquals(customer.getUsername(),customerService.createNewCustomer(customer));
    }

    @Test
    void getCustomer() throws CustomerNotFoundException {
        when(customerDAO.findById(1)).thenReturn(java.util.Optional.ofNullable(customer));
        assertEquals(customer.getUsername(),customerService.getCustomer(1));
    }

    @Test
    void deleteCustomer() throws CustomerNotFoundException {
    }

    @Test
    void getAllCustomer() {
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        customerArrayList.add(customer);
        when(customerDAO.findAll()).thenReturn(customerArrayList);
        assertEquals(customerArrayList,customerService.getAllCustomer());
    }

    @AfterClass
    void tearDown() {
        customer = null;
    }
}
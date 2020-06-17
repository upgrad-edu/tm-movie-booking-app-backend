package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CustomersController {
    @Autowired
    CustomerService customerService;
    
    @RequestMapping(value= {"/sayHelloCustomer"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From CustomerController", HttpStatus.OK);
    }

    //CUSTOMER CONTROLLER
    @PostMapping(value="/newCustomer",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    void newCustomer(@RequestBody Customer customer) {
        customerService.acceptCustomerDetails(customer);
    }

    @GetMapping("/getCustomerDetails")
    public Customer getCustomerDetails(@RequestParam int id) throws CustomerDetailsNotFoundException {
        System.out.println(customerService.getCustomerDetails(id));
        return customerService.getCustomerDetails(id);
    }

    @RequestMapping(value="/getAllCustomerDetails",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<Customer> findAllCustomers() {
        return customerService.getAllCustomerDetails();
    }

    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<String> removeCustomerDetails(@RequestParam int id) throws CustomerDetailsNotFoundException{
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Customer details successfully removed ",HttpStatus.OK);
    }
    
    
}

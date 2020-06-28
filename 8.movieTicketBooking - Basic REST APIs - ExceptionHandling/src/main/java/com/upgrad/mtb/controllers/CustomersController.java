package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.dto.CustomerDTO;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerUserNameExistsException;
import com.upgrad.mtb.exceptions.UserTypeDetailsNotFoundException;
import com.upgrad.mtb.services.BookingService;
import com.upgrad.mtb.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomersController {
    @Autowired
    CustomerService customerService;

    @Autowired
    BookingService bookingService;
    
    @RequestMapping(value= {"/sayHelloCustomer"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From CustomerController", HttpStatus.OK);
    }

    //CUSTOMER CONTROLLER
    @PostMapping(value="/customers",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity newCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerUserNameExistsException, UserTypeDetailsNotFoundException {
        Customer customer =  customerService.acceptCustomerDetails(customerDTO);
        System.out.println(customer.toString());
        
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customers/{id}")
    @ResponseBody
    public ResponseEntity getCustomerDetails(@PathVariable(name = "id") int id) throws CustomerDetailsNotFoundException {
        System.out.println(customerService.getCustomerDetails(id).toString());
        Customer customer =  customerService.getCustomerDetails(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity updateCustomerDetails(@PathVariable(name = "id") int id , @RequestBody CustomerDTO customerDTO) throws CustomerDetailsNotFoundException, UserTypeDetailsNotFoundException {
        Customer updatedCustomer =  customerService.updateCustomerDetails(id, customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @GetMapping(value="/customers",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity findAllCustomers() {
        List<Customer> customers = customerService.getAllCustomerDetails();
        System.out.println("Number of customers :" + customers.size());
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/customers/{id}")
    @ResponseBody
    public ResponseEntity<String> removeCustomerDetails(@PathVariable(name = "id") int id) throws CustomerDetailsNotFoundException{
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Customer details successfully removed ",HttpStatus.OK);
    }


    @GetMapping(value="/customers/{customerId}/bookings",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity getAllBookingsForCustomer(@PathVariable("customerId") int id) throws CustomerDetailsNotFoundException {
        Customer customer = customerService.getCustomerDetails(id);
        List<Booking> bookings = customer.getBookings();
        return  ResponseEntity.ok(bookings);
    }

    //check this
    @DeleteMapping(value="/customers/{customerId}/bookings",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity<String> deleteBookingForCustomer(@PathVariable("customerId") int customerId) throws CustomerDetailsNotFoundException, BookingDetailsNotFoundException {
        Customer customer = customerService.getCustomerDetails(customerId);
        List<Booking> bookings =  customer.getBookings();
        for(Booking booking : bookings){
            bookingService.deleteBooking(booking.getId());
            System.out.println("Booking deleted : " + booking.getId());
        }
        customer.setBookings(new ArrayList<Booking>());
        customerService.updateCustomerDetails(customer);
        System.out.println("All bookings delete for customer :" + customer.getUsername());
        return new ResponseEntity<String>("All bookings delete for customer :" +  customer.getUsername() ,HttpStatus.OK);
    }
}

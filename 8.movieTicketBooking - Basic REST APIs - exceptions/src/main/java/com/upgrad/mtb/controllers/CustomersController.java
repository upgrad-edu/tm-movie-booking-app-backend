package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerUserNameExistsException;
import com.upgrad.mtb.services.BookingService;
import com.upgrad.mtb.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Customer newCustomer(@RequestBody Customer customer) throws CustomerUserNameExistsException {
        return customerService.acceptCustomerDetails(customer);
    }

    @GetMapping("/customers/{id}")
    @ResponseBody
    public Customer getCustomerDetails(@PathVariable(name = "id") int id) throws CustomerDetailsNotFoundException {
        System.out.println(customerService.getCustomerDetails(id).toString());
        return customerService.getCustomerDetails(id);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomerDetails(@PathVariable(name = "id") int id , @RequestBody Customer customer) throws CustomerDetailsNotFoundException {
        return customerService.updateCustomerDetails(id, customer);
    }

  /*  @GetMapping("/customers")
    public Customer getCustomerDetails(@RequestParam String username) throws CustomerDetailsNotFoundException {
        System.out.println(customerService.getCustomerDetailsByUsername(username).toString());
        return customerService.getCustomerDetailsByUsername(username);
    }
*/
    @GetMapping(value="/customers",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<Customer> findAllCustomers() {
        return customerService.getAllCustomerDetails();
    }

    @DeleteMapping("/customers/{id}")
    @ResponseBody
    public ResponseEntity<String> removeCustomerDetails(@PathVariable(name = "id") int id) throws CustomerDetailsNotFoundException{
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Customer details successfully removed ",HttpStatus.OK);
    }

    @GetMapping(value="/customers/{customerId}/bookings",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<Booking> getAllBookingsForCustomer(@PathVariable("customerId") int id) throws CustomerDetailsNotFoundException {
        Customer customer = getCustomerDetails(id);
        return  customer.getBookings();
    }

    @DeleteMapping(value="/customers/{customerId}/bookings\n",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public boolean deleteBookingForCustomer(@PathVariable("customerId") int customerId) throws CustomerDetailsNotFoundException, BookingDetailsNotFoundException {
        Customer customer = getCustomerDetails(customerId);
        List<Booking> bookings =  customer.getBookings();
        for(Booking booking : bookings){
            bookingService.deleteBooking(booking.getId());
            System.out.println("Booking deleted : " + booking.getId());
        }
        System.out.println("All bookings delete for customer :" + customer.getUsername());
        return true;
    }
}

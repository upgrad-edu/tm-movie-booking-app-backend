package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.dto.BookingDTO;
import com.upgrad.mtb.dto.CustomerDTO;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import com.upgrad.mtb.exceptions.UserTypeDetailsNotFoundException;
import com.upgrad.mtb.security.jwt.JwtTokenProvider;
import com.upgrad.mtb.services.BookingService;
import com.upgrad.mtb.services.CustomerServiceImpl;
import com.upgrad.mtb.utils.DTOEntityConverter;
import com.upgrad.mtb.utils.EntityDTOConverter;
import com.upgrad.mtb.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CustomersController {
    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    BookingService bookingService;
    @Autowired
    CustomerValidator customerValidator;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    EntityDTOConverter entityDTOConverter;
    @Autowired
    DTOEntityConverter dtoEntityConverter;
    
    @RequestMapping(value= {"/sayHelloCustomer"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From CustomerController", HttpStatus.OK);
    }

    /*//CUSTOMER CONTROLLER
    @PostMapping(value="/customers",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity newCustomer(@RequestBody CustomerDTO customerDTO) throws APIException , CustomerUserNameExistsException, UserTypeDetailsNotFoundException {
        ResponseEntity responseEntity = null;
            customerValidator.validateCustomer(customerDTO);
            Customer responseCustomer = customerService.acceptCustomerDetails(customerDTO);
            responseEntity = ResponseEntity.ok(responseCustomer);
            return responseEntity;
    }*/


    @GetMapping("/customers/{id}")
    @ResponseBody
    public ResponseEntity getCustomerDetails(@PathVariable(name = "id") int id) throws CustomerDetailsNotFoundException {
        System.out.println("get customer details controller");
        Customer customer = customerService.getCustomerDetails(id);
        CustomerDTO responseCustomerDTO = entityDTOConverter.convertToCustomerDTO(customer);
        return  ResponseEntity.ok(responseCustomerDTO);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity updateCustomerDetails(@PathVariable(name = "id") int id , @RequestBody CustomerDTO customerDTO , @RequestHeader(value = "X-Access-Token") String accessToken) throws CustomerDetailsNotFoundException, UserTypeDetailsNotFoundException, TheatreDetailsNotFoundException {
        Customer savedCustomer = customerService.getUserFromAccessToken(accessToken.trim());
        if(savedCustomer == null)
            throw new CustomerDetailsNotFoundException("Customer details not found");
        Customer newCustomer = dtoEntityConverter.convertToCustomerEntity(customerDTO);
        Customer updatedCustomer =  customerService.updateCustomerDetails(id, newCustomer);
        CustomerDTO updatedCustomerDTO = entityDTOConverter.convertToCustomerDTO(updatedCustomer);
        return ResponseEntity.ok(updatedCustomerDTO);
    }

    @GetMapping(value="/customers",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity findAllCustomers(String accessToken) throws CustomerDetailsNotFoundException {
        Customer savedCustomer = customerService.getUserFromAccessToken(accessToken.trim());
        if(savedCustomer == null)
            throw new CustomerDetailsNotFoundException("Customer details not found");
        List<Customer> customers = customerService.getAllCustomerDetails();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for(Customer customer : customers){
            customerDTOList.add(entityDTOConverter.convertToCustomerDTO(customer));
        }
        System.out.println("Number of customers :" + customerDTOList.size());
        return ResponseEntity.ok(customerDTOList);
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
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        for(Booking booking : bookings){
            bookingDTOList.add(entityDTOConverter.convertToBookingDTO(booking));
        }
        return  ResponseEntity.ok(bookingDTOList);
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

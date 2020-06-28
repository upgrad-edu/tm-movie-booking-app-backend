package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.dto.BookingDTO;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.BookingFailedException;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import com.upgrad.mtb.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;

    @RequestMapping(value= {"/sayHelloBooking"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From BookingController", HttpStatus.OK);
    }

    //BOOKING CONTROLLER
    @PostMapping(value="/bookings",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity newBooking(@RequestHeader(value = "X-Access-Token") String accessToken , @RequestBody BookingDTO bookingDTO) throws TheatreDetailsNotFoundException, CustomerDetailsNotFoundException, BookingFailedException {
       Booking booking = bookingService.acceptBookingDetails(bookingDTO);
       return ResponseEntity.ok(booking);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity getBookingDetails(@RequestHeader(value = "X-Access-Token") String accessToken , @PathVariable("id") int id ) throws BookingDetailsNotFoundException {
        System.out.println(bookingService.getBookingDetails(id).toString());
        Booking booking =  bookingService.getBookingDetails(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping(value="/bookings",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity findAllBookings(@RequestHeader(value = "X-Access-Token") String accessToken) {
        List<Booking> bookings = bookingService.getAllBookingDetails();
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> removeBookingDetails(@RequestHeader(value = "X-Access-Token") String accessToken , @PathVariable("id") int id) throws BookingDetailsNotFoundException{
        bookingService.deleteBooking(id);
        return new ResponseEntity<>("Booking details successfully removed ",HttpStatus.OK);
    }
}

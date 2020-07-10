package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.dto.BookingDTO;
import com.upgrad.mtb.exceptions.*;
import com.upgrad.mtb.services.BookingService;
import com.upgrad.mtb.validator.BookingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
@CrossOrigin
@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    BookingValidator bookingValidator;

    @RequestMapping(value= {"/sayHelloBooking"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From BookingController", HttpStatus.OK);
    }

    //BOOKING CONTROLLER
    @PostMapping(value="/bookings",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity newBooking(@RequestBody BookingDTO bookingDTO) throws APIException , TheatreDetailsNotFoundException, CustomerDetailsNotFoundException, BookingFailedException {
        System.out.println("New booking");
        ResponseEntity responseEntity = null;
        try {
            bookingValidator.validateBooking(bookingDTO);
            Booking responseBooking = bookingService.acceptBookingDetails(bookingDTO);
            responseEntity = ResponseEntity.ok(responseBooking);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity getBookingDetails(@PathVariable("id") int id) throws BookingDetailsNotFoundException {
        System.out.println(bookingService.getBookingDetails(id).toString());
        Booking booking =  bookingService.getBookingDetails(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping(value="/bookings",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity findAllBookings() {
        List<Booking> bookings = bookingService.getAllBookingDetails();
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> removeBookingDetails(@PathVariable("id") int id) throws BookingDetailsNotFoundException{
        bookingService.deleteBooking(id);
        return new ResponseEntity<>("Booking details successfully removed ",HttpStatus.OK);
    }
}

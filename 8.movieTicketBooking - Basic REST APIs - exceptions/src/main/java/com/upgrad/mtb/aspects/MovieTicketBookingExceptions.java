package com.upgrad.mtb.aspects;

import com.upgrad.mtb.exceptions.*;
import com.upgrad.mtb.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieTicketBookingExceptions {

    @ExceptionHandler(CustomerDetailsNotFoundException.class)
    public ResponseEntity<CustomResponse> handleCustomerDetailsNotFoundException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return  new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(CityDetailsNotFoundException.class)
    public ResponseEntity<CustomResponse> handleCityDetailsNotFoundException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return  new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(BookingDetailsNotFoundException.class)
    public ResponseEntity<CustomResponse> handleBookingDetailsNotFoundException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return  new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(LanguageDetailsNotFoundException.class)
    public ResponseEntity<CustomResponse> handleLanguageDetailsNotFoundException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return  new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(MovieDetailsNotFoundException.class)
    public ResponseEntity<CustomResponse> handleMovieDetailsNotFoundException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return  new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(StatusDetailsNotFoundException.class)
    public ResponseEntity<CustomResponse> handleStatusDetailsNotFoundException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return  new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(TheatreDetailsNotFoundException.class)
    public ResponseEntity<CustomResponse> handleTheatreDetailsNotFoundException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return  new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(UserTypeDetailsNotFoundException.class)
    public ResponseEntity<CustomResponse> handleUserTypeDetailsNotFoundException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return  new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(CustomerUserNameExistsException.class)
    public ResponseEntity<CustomResponse> handleCustomerUserNameExistsException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
        return  new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }


}

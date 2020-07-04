package com.upgrad.mtb.validator;

import com.upgrad.mtb.dto.BookingDTO;
import com.upgrad.mtb.exceptions.APIException;

import java.text.ParseException;

public interface BookingValidator {
    public void validateBooking(BookingDTO bookingDTO) throws ParseException, APIException;
}

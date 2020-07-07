package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.dto.BookingDTO;
import com.upgrad.mtb.exceptions.*;

import java.text.ParseException;
import java.util.List;

public interface BookingService {
     public Booking acceptBookingDetails(BookingDTO bookingDTO) throws TheatreDetailsNotFoundException, CustomerDetailsNotFoundException, BookingFailedException, APIException, ParseException;
     public Booking getBookingDetails(int id) throws BookingDetailsNotFoundException;
     public boolean deleteBooking(int id) throws BookingDetailsNotFoundException;
     public List<Booking> getAllBookingDetails();
}

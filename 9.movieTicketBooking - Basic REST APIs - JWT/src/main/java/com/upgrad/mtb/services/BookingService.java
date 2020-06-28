package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.dto.BookingDTO;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.BookingFailedException;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;

import java.util.List;

public interface BookingService {
     public Booking acceptBookingDetails(BookingDTO bookingDTO) throws TheatreDetailsNotFoundException, CustomerDetailsNotFoundException, BookingFailedException;
     public Booking getBookingDetails(int id) throws BookingDetailsNotFoundException;
     public boolean deleteBooking(int id) throws BookingDetailsNotFoundException;
     public List<Booking> getAllBookingDetails();
}

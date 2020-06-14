package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;

import java.util.List;

public interface BookingService {
     public Booking acceptBookingDetails(Booking booking);
     public Booking getBookingDetails(int id) throws BookingDetailsNotFoundException;
     public boolean deleteBooking(int id) throws BookingDetailsNotFoundException;
     public List<Booking> getAllBookingDetails();
     public int addTotalSeats(int a, int b);
}

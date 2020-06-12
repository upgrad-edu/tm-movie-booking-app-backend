package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Booking;

import java.util.List;

public interface BookingDAO {
    public Booking acceptBookingDetails(Booking booking);
    public Booking getBookingDetails(int bookingId);
    public boolean deleteBooking(int bookingId);
    public List<Booking> getAllBookingDetails();
}

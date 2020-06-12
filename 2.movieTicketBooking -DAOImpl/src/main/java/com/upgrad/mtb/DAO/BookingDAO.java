package com.upgrad.mtb.DAO;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Customer;

import java.util.List;

public interface BookingDAO {
    public Booking acceptBookingDetails(Booking booking);
    public Booking getBookingDetails(int bookingId);
    public boolean deleteBooking(int bookingId);
    public List<Booking> getAllBookingDetails();
}

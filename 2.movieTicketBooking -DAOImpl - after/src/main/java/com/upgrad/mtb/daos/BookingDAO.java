package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Booking;

import java.util.List;

public interface BookingDAO {
    public Booking acceptBookingDetails(Booking booking);
    public Booking acceptBookingDetailsTransactional(Booking booking);
    public Booking getBookingDetails(int id);
    public boolean deleteBooking(int id);
    public boolean deleteBookingTransactional(int id);
    public List<Booking> getAllBookingDetails();
}

package com.upgrad.mtb.services.impl;

import com.upgrad.mtb.DAO.BookingDAO;
import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingDAO bookingDAO  ;

    public Booking acceptBookingDetails(Booking booking) {
        return bookingDAO.save(booking);
    }

    public Booking getBookingDetails(int bookingId) throws BookingDetailsNotFoundException {
        Booking booking = bookingDAO.findById(bookingId).orElseThrow(
                ()->  new BookingDetailsNotFoundException("Details not found for id : " + bookingId));
        return booking;
    }

    public boolean deleteBooking(int bookingId) throws BookingDetailsNotFoundException {
        Booking booking = getBookingDetails(bookingId);
        bookingDAO.delete(booking);
        return true;
    }

    public List<Booking> getAllBookingDetails() {
        return bookingDAO.findAll();
    }
}

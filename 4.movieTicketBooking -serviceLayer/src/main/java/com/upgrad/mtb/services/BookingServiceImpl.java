package com.upgrad.mtb.services;

import com.upgrad.mtb.daos.BookingDAO;
import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "bookingService")
@ComponentScan("com.upgrad.mtb.daos")
public class BookingServiceImpl implements BookingService  {
    @Autowired
    private BookingDAO bookingDAO  ;

    public Booking acceptBookingDetails(Booking booking) {
        return bookingDAO.save(booking);
    }

    public Booking getBookingDetails(int id) throws BookingDetailsNotFoundException {
        return bookingDAO.findById(id).orElseThrow(
                ()->  new BookingDetailsNotFoundException("Details not found for id : " + id));
    }

    public boolean deleteBooking(int id) throws BookingDetailsNotFoundException {
        Booking booking = getBookingDetails(id);
        bookingDAO.delete(booking);
        return true;
    }

    public List<Booking> getAllBookingDetails() {
        return bookingDAO.findAll();
    }
}

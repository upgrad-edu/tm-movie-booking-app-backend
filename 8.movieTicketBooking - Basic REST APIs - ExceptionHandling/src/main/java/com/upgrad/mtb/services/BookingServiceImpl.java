package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.daos.BookingDAO;
import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.dto.BookingDTO;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.BookingFailedException;
import com.upgrad.mtb.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "bookingService")
public class BookingServiceImpl implements BookingService  {
    @Autowired
    @Qualifier("bookingDAO")
    private BookingDAO bookingDAO  ;
    @Autowired
    CustomerService customerService;
    @Autowired
    TheatreService theatreService;

    public Booking acceptBookingDetails(BookingDTO bookingDTO) throws TheatreDetailsNotFoundException, CustomerDetailsNotFoundException, BookingFailedException {
        if(theatreService.getTheatreDetails(bookingDTO.getTheatreId()).getMovie().getStatus().getStatus().equals("Released")){
            Booking newBooking = new Booking();
            newBooking.setNoOfSeats(bookingDTO.getNoOfSeats());
            newBooking.setBookingDate(bookingDTO.getBookingDate());
            newBooking.setTheatre(theatreService.getTheatreDetails(bookingDTO.getTheatreId()));
            newBooking.setCustomer(customerService.getCustomerDetails(bookingDTO.getCustomerId()));
            return bookingDAO.save(newBooking);
        }else{
            throw new BookingFailedException("Booking Failed");
        }

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

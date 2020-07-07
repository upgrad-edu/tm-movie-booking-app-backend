package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.daos.BookingDAO;
import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.dto.BookingDTO;
import com.upgrad.mtb.exceptions.*;
import com.upgrad.mtb.utils.DateDifference;
import com.upgrad.mtb.validator.BookingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    BookingValidator bookingValidator;

    public Booking acceptBookingDetails(BookingDTO bookingDTO) throws TheatreDetailsNotFoundException, CustomerDetailsNotFoundException, BookingFailedException, APIException, ParseException {
        System.out.println("New booking");
        bookingValidator.validateBooking(bookingDTO);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(new Date());
        Date todaysDate = sdf.parse(dateString);
        String formatBookingDate = sdf.format(bookingDTO.getBookingDate());
        Date parsedBookingDate = sdf.parse(formatBookingDate);
        int bookingSlot = DateDifference.differenceBetweenDates(todaysDate,parsedBookingDate);
        Theatre theatre = theatreService.getTheatreDetails(bookingDTO.getTheatreId());
        if(theatre.getMovie().getStatus().getStatus().equalsIgnoreCase("Released")
            && theatre.getSeatingList().get(bookingSlot) >= bookingDTO.getNoOfSeats()
        ){
            Booking newBooking = new Booking();
            newBooking.setNoOfSeats(bookingDTO.getNoOfSeats());
            newBooking.setBookingDate(bookingDTO.getBookingDate());
            newBooking.setTheatre(theatreService.getTheatreDetails(bookingDTO.getTheatreId()));
            newBooking.setCustomer(customerService.getCustomerDetails(bookingDTO.getCustomerId()));
            List<Integer> seatingList = theatre.getSeatingList();
            seatingList.add(bookingSlot, seatingList.get(bookingSlot) - bookingDTO.getNoOfSeats());
            theatre.setSeatingList(seatingList);
            theatreService.updateTheatreDetails(bookingDTO.getTheatreId(), theatre );
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

package com.upgrad.mtb.validator;

import com.upgrad.mtb.dto.BookingDTO;
import com.upgrad.mtb.exceptions.APIException;
import com.upgrad.mtb.utils.DateDifference;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class BookingValidatorImpl implements BookingValidator {

    @Override
    public void validateBooking(BookingDTO bookingDTO) throws ParseException, APIException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(new Date());
        Date todaysDate = sdf.parse(dateString);
        String formatBookingDate = sdf.format(bookingDTO.getBookingDate());
        Date parsedBookingDate = sdf.parse(formatBookingDate);
        if(bookingDTO.getCustomerId() <= 0)
            throw new APIException("Invalid userId");
        if(bookingDTO.getNoOfSeats() <= 0)
            throw new APIException("Invalid number of seats");
        if(bookingDTO.getTheatreId() <= 0)
            throw new APIException("Invalid theatreID");
        int dateDifferece = DateDifference.differenceBetweenDates(todaysDate,parsedBookingDate);
        if(dateDifferece < 0 || dateDifferece >= 3)
            throw new APIException("Invalid booking data");
    }
}

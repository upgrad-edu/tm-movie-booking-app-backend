package com.upgrad.mtb.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookingDTO {
    Date bookingDate;
    int noOfSeats;
    int customerId;
    int theatreId;
}

package com.upgrad.mtb.dto;

import com.upgrad.mtb.beans.Booking;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TheatreDTO {
    String theatreName;
    int noOfSeats;
    int ticketPrice;
    int cityId;
    int movieId;
    List<Booking> bookings;
}

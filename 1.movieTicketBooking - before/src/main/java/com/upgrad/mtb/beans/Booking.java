package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Booking {
    private int id;
    private int customerId;
    private int theatreId;
    private Date bookingDate;
    private int noOfSeats;

    public Booking(){}

    public Booking(int id, int customerId, int theatreId, Date bookingDate, int noOfSeats) {
        this.id = id;
        this.customerId = customerId;
        this.theatreId = theatreId;
        this.bookingDate = bookingDate;
        this.noOfSeats = noOfSeats;
    }
}

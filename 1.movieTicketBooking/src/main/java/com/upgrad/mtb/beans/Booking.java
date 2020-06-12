package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}

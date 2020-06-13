package com.upgrad.mtb.beans;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private int customerId;
    @NotNull
    private int theatreId;
    @NotNull
    private Date bookingDate;
    @NotNull
    private int noOfSeats;

    public Booking(){}

}

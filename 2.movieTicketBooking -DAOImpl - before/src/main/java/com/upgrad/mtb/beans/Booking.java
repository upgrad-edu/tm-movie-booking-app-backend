package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private int customerId;
    @Column(nullable = false)
    private int theatreId;
    @Column(nullable = false)
    private Date bookingDate;
    @Column(nullable = false)
    private int noOfSeats;

    public Booking(){}

}

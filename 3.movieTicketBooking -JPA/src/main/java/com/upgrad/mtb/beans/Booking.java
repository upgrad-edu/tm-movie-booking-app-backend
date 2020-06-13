package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
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
    @NotNull
    private Date bookingDate;
    @NotNull
    private int noOfSeats;
    @ManyToOne
    @JsonBackReference("booking_theatre")
    private Theatre theatre;
    @ManyToOne
    @JsonBackReference("booking_theatre")
    private Customer customer;


    public Booking(){}

}

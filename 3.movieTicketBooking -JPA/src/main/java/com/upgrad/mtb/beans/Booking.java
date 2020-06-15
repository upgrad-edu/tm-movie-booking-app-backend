package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column( nullable = false)
    private Date bookingDate;
    @Column( nullable = false)
    private int noOfSeats;
    @ManyToOne
    @JsonBackReference("booking_theatre")
    private Theatre theatre;
    @ManyToOne
    @JsonBackReference("booking_theatre")
    private Customer customer;


    public Booking(){}

}

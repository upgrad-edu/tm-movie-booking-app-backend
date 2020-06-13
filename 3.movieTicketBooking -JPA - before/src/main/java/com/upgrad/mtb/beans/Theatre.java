package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(unique = true)
    private String theatreName;
    @NotNull
    private int cityId;
    @NotNull
    private int noOfSeats;
    @NotNull
    private int ticketPrice;


   /* List<Booking> bookings;*/

    @ManyToOne
    @JsonBackReference("movie_theatre")
    private Movie movie;

    public Theatre(){}

}

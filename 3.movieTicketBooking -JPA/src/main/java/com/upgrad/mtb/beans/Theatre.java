package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int cityId;
    private int noOfSeats;
    private int ticketPrice;

    @OneToMany(mappedBy = "theatre" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @MapKey
    @JsonManagedReference("booking_theatre")
    List<Booking> bookings;

    @ManyToMany(mappedBy = "movieTheatres")
    Set<Movie> movies;

    public Theatre(){}

}

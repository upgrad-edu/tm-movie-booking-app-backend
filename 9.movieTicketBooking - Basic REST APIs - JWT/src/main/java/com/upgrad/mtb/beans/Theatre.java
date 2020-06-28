package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column( nullable = false, unique = true)
    private String theatreName;
    @Column( nullable = false)
    private int noOfSeats;
    @Column( nullable = false)
    private int ticketPrice;

    @ManyToOne
    @JsonBackReference("city_theatre")
    private City city;

    @OneToMany(mappedBy = "theatre" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @MapKey
    @JsonManagedReference("theatre_booking")
    List<Booking> bookings;

    @ManyToOne
    @JsonBackReference("movie_theatre")
    private Movie movie;



    public Theatre(){}

    public Theatre(String theatreName, int noOfSeats, int ticketPrice, City city, List<Booking> bookings, Movie movie) {
        this.theatreName = theatreName;
        this.noOfSeats = noOfSeats;
        this.ticketPrice = ticketPrice;
        this.city = city;
        this.bookings = bookings;
        this.movie = movie;
    }
}

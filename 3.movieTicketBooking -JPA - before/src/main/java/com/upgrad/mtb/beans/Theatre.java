package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column( nullable = false, unique = true)
    private String name;
    @Column( nullable = false)
    private int noOfSeats;
    @Column( nullable = false)
    private int ticketPrice;


    @ManyToMany
    @JoinTable(name="TheatreMovieDetails" ,joinColumns = @JoinColumn(name="THEATREID"),inverseJoinColumns = @JoinColumn(name="MOVIEID"))

    private List<Movie> movies;

    /*@ManyToOne
    private City city;



    @OneToMany(mappedBy = "theatre" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    List<Booking> bookings;
    */

    public Theatre(){}

    public Theatre(int id, String name, int noOfSeats, int ticketPrice) {
        this.id = id;
        this.name = name;
        this.noOfSeats = noOfSeats;
        this.ticketPrice = ticketPrice;
    }

    /*public Theatre(String name, int noOfSeats, int ticketPrice, City city, List<Booking> bookings, Movie movie) {
        this.name = name;
        this.noOfSeats = noOfSeats;
        this.ticketPrice = ticketPrice;
        this.city = city;
        this.bookings = bookings;
        this.movie = movie;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String theatreName) {
        this.name = name;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

}

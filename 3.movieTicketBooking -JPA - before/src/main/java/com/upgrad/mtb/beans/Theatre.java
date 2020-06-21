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
    private String theatreName;
    @Column( nullable = false)
    private int noOfSeats;
    @Column( nullable = false)
    private int ticketPrice;

    /*@ManyToOne
    private City city;

    @OneToMany(mappedBy = "theatre" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    List<Booking> bookings;

    @ManyToOne
    private Movie movie;*/

    public Theatre(){}

    public Theatre(int id, String theatreName, int noOfSeats, int ticketPrice) {
        this.id = id;
        this.theatreName = theatreName;
        this.noOfSeats = noOfSeats;
        this.ticketPrice = ticketPrice;
    }

    /*public Theatre(String theatreName, int noOfSeats, int ticketPrice, City city, List<Booking> bookings, Movie movie) {
        this.theatreName = theatreName;
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

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
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

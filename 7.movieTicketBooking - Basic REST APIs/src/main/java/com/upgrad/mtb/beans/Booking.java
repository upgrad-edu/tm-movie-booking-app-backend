package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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
    @JsonBackReference("theatre_booking")
    private Theatre theatre;
    @ManyToOne
    @JsonBackReference("customer_booking")
    private Customer customer;


    public Booking(){}

    public Booking(Date bookingDate, int noOfSeats, Theatre theatre, Customer customer) {
        this.bookingDate = bookingDate;
        this.noOfSeats = noOfSeats;
        this.theatre = theatre;
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return noOfSeats == booking.noOfSeats &&
                bookingDate.equals(booking.bookingDate) &&
                theatre.equals(booking.theatre) &&
                customer.equals(booking.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingDate, noOfSeats, theatre, customer);
    }
}

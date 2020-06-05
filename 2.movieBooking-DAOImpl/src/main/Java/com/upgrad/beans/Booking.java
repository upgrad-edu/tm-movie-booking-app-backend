package com.upgrad.beans;

import java.util.Date;
import java.util.Objects;

public class Booking {
    int bookingId;
    int customerId;
    int theatreId;
    Date bookingDate;
    int noOfSeats;

    public Booking(int bookingId, int customerId, int theatreId, Date bookingDate, int noOfSeats) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.theatreId = theatreId;
        this.bookingDate = bookingDate;
        this.noOfSeats = noOfSeats;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId &&
                customerId == booking.customerId &&
                theatreId == booking.theatreId &&
                noOfSeats == booking.noOfSeats &&
                Objects.equals(bookingDate, booking.bookingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, customerId, theatreId, bookingDate, noOfSeats);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", theatreId=" + theatreId +
                ", bookingDate=" + bookingDate +
                ", noOfSeats=" + noOfSeats +
                '}';
    }
}

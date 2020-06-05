package com.upgrad.beans;

import java.util.List;
import java.util.Objects;

public class Theatre {
    int theatreId;
    int cityId;
    int noOfSeats;
    int ticketPrice;
    List<Movie> movies;
    List<Booking> bookings;

    public Theatre(){}

    public Theatre(int theatreId, int cityId, int noOfSeats, int ticketPrice, List<Movie> movies, List<Booking> bookings) {
        this.theatreId = theatreId;
        this.cityId = cityId;
        this.noOfSeats = noOfSeats;
        this.ticketPrice = ticketPrice;
        this.movies = movies;
        this.bookings = bookings;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theatre theatre = (Theatre) o;
        return theatreId == theatre.theatreId &&
                cityId == theatre.cityId &&
                noOfSeats == theatre.noOfSeats &&
                ticketPrice == theatre.ticketPrice &&
                Objects.equals(movies, theatre.movies) &&
                Objects.equals(bookings, theatre.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theatreId, cityId, noOfSeats, ticketPrice, movies, bookings);
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "threatreId=" + theatreId +
                ", cityId=" + cityId +
                ", noOfSeats=" + noOfSeats +
                ", ticketPrice=" + ticketPrice +
                ", movies=" + movies +
                ", bookings=" + bookings +
                '}';
    }
}

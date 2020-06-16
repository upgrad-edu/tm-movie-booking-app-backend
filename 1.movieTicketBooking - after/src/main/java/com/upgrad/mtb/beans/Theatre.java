package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Theatre {
    private int id;
    private String name;
    private int noOfSeats;
    private int ticketPrice;

    public Theatre(){}

    public Theatre(int id, String name, int noOfSeats, int ticketPrice) {
        this.id = id;
        this.name = name;
        this.noOfSeats = noOfSeats;
        this.ticketPrice = ticketPrice;
    }
}

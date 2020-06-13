package com.upgrad.mtb.beans;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String theatreName;
    @NotNull
    private int cityId;
    @NotNull
    private int noOfSeats;
    @NotNull
    private int ticketPrice;

    public Theatre(){}

}

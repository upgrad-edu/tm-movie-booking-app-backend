package com.upgrad.mtb.beans;

import javax.persistence.*;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String theatreName;
    @Column(nullable = false)
    private int cityId;
    @Column(nullable = false)
    private int noOfSeats;
    @Column(nullable = false)
    private int ticketPrice;

    public Theatre(){}

}

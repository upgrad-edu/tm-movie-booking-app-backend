package com.upgrad.mtb.beans;

import javax.persistence.*;
import java.util.List;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int cityId;
    private int noOfSeats;
    private int ticketPrice;

    public Theatre(){}

}

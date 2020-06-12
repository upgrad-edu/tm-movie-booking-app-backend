package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class Theatre {
    private int id;
    private int cityId;
    private int noOfSeats;
    private int ticketPrice;

    public Theatre(){}

}

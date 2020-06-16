package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

public class Status {

    private int id;

    private String status;

    public Status(){

    }
}

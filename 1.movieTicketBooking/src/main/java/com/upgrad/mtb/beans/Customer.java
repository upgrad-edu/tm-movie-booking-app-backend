package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Getter
@Setter
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Date dateOfBirth;
    private int userTypeId;
    private String phoneNumber;

    public Customer() {
    }
}

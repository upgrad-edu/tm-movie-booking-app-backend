package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

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

    public Customer(int id, String firstName, String lastName, String username, String password, Date dateOfBirth, int userTypeId, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.userTypeId = userTypeId;
        this.phoneNumber = phoneNumber;
    }
}

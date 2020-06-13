package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    private String password;
    private Date dateOfBirth;
    private int userTypeId;
    private String phoneNumber;

    public Customer() {
    }
}

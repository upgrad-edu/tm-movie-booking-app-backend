package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(unique = true , nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Date dateOfBirth;
    private int userTypeId;
    @Column(nullable = false)
    @ElementCollection
    private List<String> phoneNumber;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String username, String password, Date dateOfBirth, int userTypeId, List<String> phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.userTypeId = userTypeId;
        this.phoneNumber = phoneNumber;
    }
}

package com.upgrad.mtb.beans;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String firstName;
    private String lastName;
    @Column(unique = true)
    @NotNull
    private String username;
    @Size(min = 5, max = 32)
    @NotNull
    private String password;
    @NotNull
    private Date dateOfBirth;
    private int userTypeId;
    @Size(min = 10 , max = 10)
    @NotNull
    private String phoneNumber;

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", userTypeId=" + userTypeId +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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
    @Size(min = 10 , max = 10)
    @NotNull
    private String phoneNumber;

    @OneToMany(mappedBy = "customer" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @MapKey
    @JsonManagedReference("booking_customer")
    List<Booking> bookings;
    @OneToOne
    UserType userType;

    public Customer() {
    }
}

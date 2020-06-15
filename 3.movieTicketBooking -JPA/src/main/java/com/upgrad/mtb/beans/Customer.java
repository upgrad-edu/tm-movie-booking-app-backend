package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Column( nullable = false)
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String username;
    @Size(min = 5, max = 32)
    @Column( nullable = false)
    private String password;
    @Column( nullable = false)
    private Date dateOfBirth;
    @Size(min = 10 , max = 10)
    @Column( nullable = false)
    @ElementCollection
    private List<String> phoneNumbers;

    @OneToMany(mappedBy = "customer" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @MapKey
    @JsonManagedReference("booking_customer")
    List<Booking> bookings;
    @ManyToOne
    @JsonBackReference("booking_customer")
    UserType userType;

    public Customer() {
    }
}

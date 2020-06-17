package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    @Column( nullable = false)
    private String password;
    @Column( nullable = false)
    private Date dateOfBirth;
    @Column( nullable = false)
    @ElementCollection
    private List<String> phoneNumbers;

    @OneToMany(mappedBy = "customer" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    List<Booking> bookings;
    @ManyToOne
    UserType userType;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String username , Date dateOfBirth,  List<String> phoneNumbers, List<Booking> bookings, UserType userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumbers = phoneNumbers;
        this.bookings = bookings;
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return firstName.equals(customer.firstName) &&
                lastName.equals(customer.lastName) &&
                username.equals(customer.username) &&
                password.equals(customer.password) &&
                dateOfBirth.equals(customer.dateOfBirth) &&
                phoneNumbers.equals(customer.phoneNumbers) &&
                bookings.equals(customer.bookings) &&
                userType.equals(customer.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, password, dateOfBirth, phoneNumbers, bookings, userType);
    }
}

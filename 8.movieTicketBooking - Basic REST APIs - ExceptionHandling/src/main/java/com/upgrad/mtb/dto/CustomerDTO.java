package com.upgrad.mtb.dto;

import com.upgrad.mtb.beans.Booking;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerDTO {
    String firstName;
    String lastName;
    Date dateOfBirth;
    String username;
    String password;
    int userTypeId;
    List<String> phoneNumbers;
    List<Booking> bookings;
}

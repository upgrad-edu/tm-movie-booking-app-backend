package com.upgrad.mtb.dto;

import com.upgrad.mtb.beans.Booking;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerDTO {
    int customerId;
    String firstName;
    String lastName;
    Date dateOfBirth;
    String username;
    String password;
    int userTypeId;
    String jwtToken;
    String refreshToken;
    List<String> phoneNumbers;
    List<BookingDTO> bookings;
}

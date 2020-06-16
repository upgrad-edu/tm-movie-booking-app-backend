package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(unique = true , nullable = false)
    String userType;

    public UserType() {
    }

    public UserType(String userType) {
        this.userType = userType;
    }
}

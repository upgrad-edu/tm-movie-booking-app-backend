package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(unique = true , nullable = false)
    String userType;
    @OneToMany(mappedBy = "userType" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    List<Customer> customer;

    public UserType() {
    }

    public UserType(int id, String userType) {
        this.id = id;
        this.userType = userType;
    }

    public UserType(String userType){
        this.userType = userType;
    }
}

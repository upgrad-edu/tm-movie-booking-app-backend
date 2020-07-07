package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @JsonManagedReference("userType_customer")
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

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", userType='" + userType + '\'' +
                '}';
    }
}

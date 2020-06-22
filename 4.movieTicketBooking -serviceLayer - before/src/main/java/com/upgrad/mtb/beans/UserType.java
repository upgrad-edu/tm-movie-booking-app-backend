package com.upgrad.mtb.beans;

import javax.persistence.*;

@Entity
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(unique = true, nullable = false)
    String type;
   /* @OneToMany(mappedBy = "userType" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    List<Customer> customer;*/

    public UserType() {
    }

    public UserType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

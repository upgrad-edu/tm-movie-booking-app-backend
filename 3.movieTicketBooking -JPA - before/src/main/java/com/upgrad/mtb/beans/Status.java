package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String status;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "status")
    List<Movie> movies;

    public Status(){
    }

    public Status(int id, String status){
        this.id = id;
        this.status = status;
    }

    public Status(String status){
        this.status = status;
    }
}

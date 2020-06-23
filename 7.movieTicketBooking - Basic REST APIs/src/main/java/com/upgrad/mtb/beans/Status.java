package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String status;

    @OneToMany(mappedBy = "status" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @MapKey
    @JsonManagedReference("status_movie")
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

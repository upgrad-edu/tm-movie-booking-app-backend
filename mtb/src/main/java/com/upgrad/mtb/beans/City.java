package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String city;

    @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @MapKey
    @JsonManagedReference("theatre_city")
   List<Theatre> theatres;

    public City() {
    }

    public City(String city, List<Theatre> theatres) {
        this.city = city;
        this.theatres = theatres;
    }

    public City(String city) {
        this.city = city;
    }
}

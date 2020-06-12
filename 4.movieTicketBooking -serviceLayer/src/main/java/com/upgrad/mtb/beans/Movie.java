package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private Date releaseDate;
    private int duration;
    private String coverPhotoURL;
    private String trailerURL;
    private int theatreId;

    @ManyToOne
    @JsonBackReference("movie_language")
    private Language language;

    @ManyToOne
    @JsonBackReference("movie_status")
    private Status status;

    @ManyToMany
    Set<Theatre> theatres;



    public Movie(){}

}

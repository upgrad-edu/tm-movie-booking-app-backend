package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Date releaseDate;
    @NotNull
    private int duration;
    @NotNull
    private String coverPhotoURL;
    @NotNull
    private String trailerURL;

    @ManyToOne
    @JsonBackReference("movie_language")
    private Language language;

    @ManyToOne
    @JsonBackReference("movie_status")
    private Status status;

    @OneToMany(mappedBy = "movie" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @MapKey
    @JsonManagedReference("movie_theatre")
    List<Theatre> theatres;



    public Movie(){}

}

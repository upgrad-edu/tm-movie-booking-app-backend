package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false , unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Date releaseDate;
    @Column(nullable = false)
    private int duration;
    @Column(nullable = false)
    private String coverPhotoURL;
    @Column(nullable = false)
    private String trailerURL;
    @Column(nullable = false)
    private int statusId;
    @Column(nullable = false)
    private int languageId;
    @Column(nullable = false)
    private int theatreId;

    public Movie(){}

}

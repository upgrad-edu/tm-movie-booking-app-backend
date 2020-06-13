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
    private String name;
    private String description;
    private Date releaseDate;
    private int duration;
    @Column(unique = true)
    private String coverPhotoURL;
    @Column(unique = true)
    private String trailerURL;
    private int statusId;
    private int languageId;
    private int theatreId;

    public Movie(){}

}

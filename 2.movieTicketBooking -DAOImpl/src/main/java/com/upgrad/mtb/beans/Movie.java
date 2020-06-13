package com.upgrad.mtb.beans;

import com.sun.istack.NotNull;
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
    @NotNull
    @Column(unique = true)
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
    @NotNull
    private int statusId;
    @NotNull
    private int languageId;
    @NotNull
    private int theatreId;

    public Movie(){}

}

package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
public class Movie {
    private int id;
    private String name;
    private String description;
    private Date releaseDate;
    private int duration;
    private String coverPhotoURL;
    private String trailerURL;
    private int statusId;
    private int languageId;
    private int theatreId;

    public Movie(){}

}

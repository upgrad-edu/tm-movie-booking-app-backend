package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;
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

    public Movie(){}

    public Movie(int id, String name, String description, Date releaseDate, int duration, String coverPhotoURL, String trailerURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.coverPhotoURL = coverPhotoURL;
        this.trailerURL = trailerURL;
    }
}

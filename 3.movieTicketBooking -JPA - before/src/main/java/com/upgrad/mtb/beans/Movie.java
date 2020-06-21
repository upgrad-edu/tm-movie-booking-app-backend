package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column( nullable = false)
    private String name;
    @Column( nullable = false)
    private String description;
    @Column( nullable = false)
    private Date releaseDate;
    @Column( nullable = false)
    private int duration;
    @Column( nullable = false)
    private String coverPhotoURL;
    @Column( nullable = false)
    private String trailerURL;

   /* @ManyToOne
    @JoinColumn(name="languageId")
    private Language language;*/


   // private Status status;

    /*

    @ManyToMany(mappedBy = "movie" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    List<Theatre> theatres;

    @ManyToOne
    private Status status;
    */

    public Movie(){}

    public Movie(String name, String description, Date releaseDate, int duration, String coverPhotoURL, String trailerURL) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.coverPhotoURL = coverPhotoURL;
        this.trailerURL = trailerURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCoverPhotoURL() {
        return coverPhotoURL;
    }

    public void setCoverPhotoURL(String coverPhotoURL) {
        this.coverPhotoURL = coverPhotoURL;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
                ", coverPhotoURL='" + coverPhotoURL + '\'' +
                ", trailerURL='" + trailerURL + '\'' +
                '}';
    }
}

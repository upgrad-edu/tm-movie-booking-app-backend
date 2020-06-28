package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference("language_movie")
    private Language language;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference("status_movie")
    private Status status;

    @OneToMany(mappedBy = "movie" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @MapKey
    @JsonManagedReference("movie_theatre")
    List<Theatre> theatres;

    public Movie(){}

    public Movie(String name, String description, Date releaseDate, int duration, String coverPhotoURL, String trailerURL, Language language, Status status, List<Theatre> theatres) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.coverPhotoURL = coverPhotoURL;
        this.trailerURL = trailerURL;
        this.language = language;
        this.status = status;
        this.theatres = theatres;
    }

    public Movie(String name, String description, Date releaseDate, int duration, String coverPhotoURL, String trailerURL, Language language, Status status) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.coverPhotoURL = coverPhotoURL;
        this.trailerURL = trailerURL;
        this.language = language;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return duration == movie.duration &&
                name.equals(movie.name) &&
                description.equals(movie.description) &&
                releaseDate.equals(movie.releaseDate) &&
                coverPhotoURL.equals(movie.coverPhotoURL) &&
                trailerURL.equals(movie.trailerURL) &&
                language.equals(movie.language) &&
                status.equals(movie.status) &&
                theatres.equals(movie.theatres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, releaseDate, duration, coverPhotoURL, trailerURL, language, status, theatres);
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
                ", language=" + language +
                ", status=" + status +
                ", theatres=" + theatres +
                '}';
    }
}

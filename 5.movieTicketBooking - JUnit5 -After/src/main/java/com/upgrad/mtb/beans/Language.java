package com.upgrad.mtb.beans;

import javax.persistence.*;
import java.util.List;


@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String language;

    @OneToMany(mappedBy = "language",cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    List<Movie>movies;



    public Language() {
    }

    public Language(String language){
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

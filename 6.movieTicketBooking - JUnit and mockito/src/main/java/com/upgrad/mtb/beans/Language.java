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

    public Language(int id, String language) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Language)) return false;

        Language language1 = (Language) o;

        if (id != language1.id) return false;
        if (language != null ? !language.equals(language1.language) : language1.language != null) return false;
        return movies != null ? movies.equals(language1.movies) : language1.movies == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (movies != null ? movies.hashCode() : 0);
        return result;
    }
}

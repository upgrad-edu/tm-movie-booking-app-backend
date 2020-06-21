package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String language;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    @JoinTable(name = "LanguageMovieDetails",
                joinColumns = @JoinColumn(name="LanguageId"),
                    inverseJoinColumns = @JoinColumn(name="MovieID"))
    List<Movie> movies;

    public Language() {
    }

    public Language(String language){
        this.language = language;
    }
}

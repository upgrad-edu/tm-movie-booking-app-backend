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

    @OneToMany(mappedBy = "language")
    List<Movie>movies;



    public Language() {
    }

    public Language(String language){
        this.language = language;
    }
}

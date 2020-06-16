package com.upgrad.mtb.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Language {
    private int id;
    private String language;

    public Language(){}

    public Language(String language) {
        this.language = language;
    }
}

package com.upgrad.mtb.dto;

import com.upgrad.mtb.beans.Theatre;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class MovieDTO {
    String coverURL;
    String description;
    int duration;
    String name;
    Date releaseDate;
    String trailerURL;
    int languageId;
    int statusId;
    List<Theatre> theatres;
}

package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.dto.TheatreDTO;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;

import java.util.List;

public interface TheatreService {
    public Theatre acceptTheatreDetails(Theatre theatre);
    public Theatre getTheatreDetails(int id) throws TheatreDetailsNotFoundException;
    public Theatre updateTheatreDetails(int theatreId, Theatre theatre) throws TheatreDetailsNotFoundException;
    public boolean deleteTheatre(int id) throws TheatreDetailsNotFoundException;
    public List<Theatre> getAllTheatreDetails();
}

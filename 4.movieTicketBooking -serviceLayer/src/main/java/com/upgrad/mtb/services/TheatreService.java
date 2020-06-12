package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;

import java.util.List;

public interface TheatreService {
    public Theatre acceptTheatreDetails(Theatre theatre);
    public Theatre getTheatreDetails(int theatreId) throws TheatreDetailsNotFoundException;
    public boolean deleteTheatre(int theatreId) throws TheatreDetailsNotFoundException;
    public List<Theatre> getAllTheatreDetails();
}

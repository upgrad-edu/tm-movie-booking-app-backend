package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Theatre;

import java.util.List;

public interface TheatreDAO {
    public Theatre acceptTheatreDetails(Theatre theatre);
    public Theatre getTheatreDetails(int theatreId);
    public boolean deleteTheatre(int theatreId);
    public List<Theatre> getAllTheatreDetails();
}

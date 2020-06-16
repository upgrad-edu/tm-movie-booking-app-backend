package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Theatre;

import java.util.List;

public interface TheatreDAO {
    public Theatre acceptTheatreDetails(Theatre theatre);
    public Theatre acceptTheatreDetailsTransactional(Theatre theatre);
    public Theatre getTheatreDetails(int id);
    public boolean deleteTheatre(int id);
    public boolean deleteTheatreTransactional(int id);
    public List<Theatre> getAllTheatreDetails();
}

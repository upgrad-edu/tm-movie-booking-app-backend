package com.upgrad.mtb.services.impl;

import com.upgrad.mtb.DAO.TheatreDAO;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import com.upgrad.mtb.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TheatreServiceImpl implements TheatreService {
    @Autowired
    TheatreDAO theatreDAO;

    @Override
    public Theatre acceptTheatreDetails(Theatre theatre) {
        return theatreDAO.save(theatre);
    }

    @Override
    public Theatre getTheatreDetails(int theatreId) throws TheatreDetailsNotFoundException {
        Theatre theatre = theatreDAO.findById(theatreId).orElseThrow(
                ()->  new TheatreDetailsNotFoundException("Theatre not found for " + theatreId));
        return theatre;
    }

    @Override
    public boolean deleteTheatre(int theatreId) throws TheatreDetailsNotFoundException {
        Theatre theatre = getTheatreDetails(theatreId);
        theatreDAO.delete(theatre);
        return true;
    }

    @Override
    public List<Theatre> getAllTheatreDetails() {
        return theatreDAO.findAll();
    }
}

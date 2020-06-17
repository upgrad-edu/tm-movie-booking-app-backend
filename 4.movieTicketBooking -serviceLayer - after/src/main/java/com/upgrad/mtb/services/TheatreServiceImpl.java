package com.upgrad.mtb.services;

import com.upgrad.mtb.daos.TheatreDAO;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "theatreService")
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    TheatreDAO theatreDAO;

    @Override
    public Theatre acceptTheatreDetails(Theatre theatre) {
        return theatreDAO.save(theatre);
    }

    @Override
    public Theatre getTheatreDetails(int id) throws TheatreDetailsNotFoundException {
        Theatre theatre = theatreDAO.findById(id).orElseThrow(
                ()->  new TheatreDetailsNotFoundException("Theatre not found for " + id));
        return theatre;
    }

    @Override
    public boolean deleteTheatre(int id) throws TheatreDetailsNotFoundException {
        Theatre theatre = getTheatreDetails(id);
        theatreDAO.delete(theatre);
        return true;
    }

    @Override
    public List<Theatre> getAllTheatreDetails() {
        return theatreDAO.findAll();
    }
}

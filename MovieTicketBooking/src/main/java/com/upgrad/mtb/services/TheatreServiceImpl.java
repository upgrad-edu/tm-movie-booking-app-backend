package com.upgrad.mtb.services;

import com.upgrad.mtb.daos.TheatreDAO;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.dto.TheatreDTO;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service(value = "theatreService")
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    @Qualifier("theatreDAO")
    TheatreDAO theatreDAO;
    @Autowired
    MovieService movieService;
    @Autowired
    CityService cityService;

    @Override
    public Theatre acceptTheatreDetails(Theatre theatre){
        List<Integer> seatingList = new ArrayList<>();
        for(int i=0;i<3;i++){
            seatingList.add(theatre.getNoOfSeats());
        }
        theatre.setSeatingList(seatingList);
        return theatreDAO.save(theatre);
    }

    @Override
    public Theatre getTheatreDetails(int id) throws TheatreDetailsNotFoundException {
        Theatre theatre = theatreDAO.findById(id).orElseThrow(
                ()->  new TheatreDetailsNotFoundException("Theatre not found for " + id));
        return theatre;
    }

    public Theatre updateTheatreDetails(int theatreId, Theatre theatre) throws TheatreDetailsNotFoundException {
        Theatre savedTheatre = getTheatreDetails(theatreId);
        savedTheatre.setTicketPrice(theatre.getTicketPrice());
        savedTheatre.setTheatreName(theatre.getTheatreName());
        savedTheatre.setNoOfSeats(theatre.getNoOfSeats());
        savedTheatre.setBookings(theatre.getBookings());
        savedTheatre.setMovie(theatre.getMovie());
        savedTheatre.setCity(theatre.getCity());
        savedTheatre.setSeatingList(theatre.getSeatingList());
        return theatreDAO.save(savedTheatre);
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

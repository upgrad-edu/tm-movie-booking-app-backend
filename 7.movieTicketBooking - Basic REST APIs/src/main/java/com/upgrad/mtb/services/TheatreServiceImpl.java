package com.upgrad.mtb.services;

import com.upgrad.mtb.daos.TheatreDAO;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.dto.TheatreDTO;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
    public Theatre acceptTheatreDetails(TheatreDTO theatreDTO) throws MovieDetailsNotFoundException {
        Theatre newTheatre = new Theatre();
        newTheatre.setTheatreName(theatreDTO.getTheatreName());
        newTheatre.setNoOfSeats(theatreDTO.getNoOfSeats());
        newTheatre.setTicketPrice(theatreDTO.getTicketPrice());
        newTheatre.setMovie(movieService.getMovieDetails(theatreDTO.getMovieId()));
        newTheatre.setCity(cityService.getCityDetails(theatreDTO.getCityId()));
        newTheatre.setBookings(theatreDTO.getBookings());
        return theatreDAO.save(newTheatre);
    }

    @Override
    public Theatre getTheatreDetails(int id) throws TheatreDetailsNotFoundException {
        Theatre theatre = theatreDAO.findById(id).orElseThrow(
                ()->  new TheatreDetailsNotFoundException("Theatre not found for " + id));
        return theatre;
    }

    @Override
    public Theatre updateTheatreDetails(int id, TheatreDTO theatreDTO) throws TheatreDetailsNotFoundException, MovieDetailsNotFoundException {
        Theatre initialTheatre = getTheatreDetails(id);
        initialTheatre.setTheatreName(theatreDTO.getTheatreName());
        initialTheatre.setNoOfSeats(theatreDTO.getNoOfSeats());
        initialTheatre.setTicketPrice(theatreDTO.getTicketPrice());
        initialTheatre.setMovie(movieService.getMovieDetails(theatreDTO.getMovieId()));
        initialTheatre.setCity(cityService.getCityDetails(theatreDTO.getCityId()));
        initialTheatre.setBookings(theatreDTO.getBookings());
        return theatreDAO.save(initialTheatre);
    }

    public Theatre updateTheatreDetails(Theatre theatre){
        return theatreDAO.save(theatre);
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

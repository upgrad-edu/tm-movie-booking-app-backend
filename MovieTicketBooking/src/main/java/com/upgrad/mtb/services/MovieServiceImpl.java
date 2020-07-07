package com.upgrad.mtb.services;

import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.exceptions.*;
import com.upgrad.mtb.utils.DTOEntityConverter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {
    @Autowired
    @Qualifier("movieDAO")
    private MovieDAO movieDAO ;
    @Autowired
    LanguageService languageService;
    @Autowired
    StatusService statusService;
    @Autowired
    DTOEntityConverter dtoEntityConverter;

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Override
    public Movie acceptMovieDetails(Movie movie) {
        logger.debug("Enter accept movie details: SAVE" ,movie);
        return movieDAO.save(movie);
    }

    @Override
    public Movie getMovieDetails(int id) throws MovieDetailsNotFoundException {
        Movie movie = movieDAO.findById(id).orElseThrow(
                ()->  new MovieDetailsNotFoundException("movie not found for " + id));
        return movie;
    }

    public Movie updateMovieDetails(int movieId, Movie movie) throws MovieDetailsNotFoundException {
        Movie savedMovie = getMovieDetails(movieId);
        savedMovie.setDescription(movie.getDescription());
        savedMovie.setName(movie.getName());
        savedMovie.setTrailerURL(movie.getTrailerURL());
        savedMovie.setDuration(movie.getDuration());
        savedMovie.setReleaseDate(movie.getReleaseDate());
        savedMovie.setCoverPhotoURL(movie.getCoverPhotoURL());
        savedMovie.setTheatres(movie.getTheatres());
        savedMovie.setStatus(movie.getStatus());
        savedMovie.setLanguage(movie.getLanguage());
        return movieDAO.save(savedMovie);
    }


    @Override
    public boolean deleteMovie(int id) throws MovieDetailsNotFoundException {
        Movie movie = getMovieDetails(id);
        movieDAO.delete(movie);
        return true;
    }

    @Override
    public List<Movie> getAllMoviesDetails() {
        return movieDAO.findAll();
    }
}

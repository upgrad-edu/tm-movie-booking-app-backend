package com.upgrad.mtb.services.impl;

import com.upgrad.mtb.DAO.MovieDAO;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDAO movieDAO ;

    @Override
    public Movie acceptMovieDetails(Movie movie) {
        return movieDAO.save(movie);
    }

    @Override
    public Movie getMovieDetails(int movieId) throws MovieDetailsNotFoundException {
        Movie movie = movieDAO.findById(movieId).orElseThrow(
                ()->  new MovieDetailsNotFoundException("movie not found for " + movieId));
        return movie;
    }

    @Override
    public boolean deleteMovie(int movieId) throws MovieDetailsNotFoundException {
        Movie movie = getMovieDetails(movieId);
        movieDAO.delete(movie);
        return true;
    }

    @Override
    public List<Movie> getAllMoviesDetails() {
        return movieDAO.findAll();
    }
}

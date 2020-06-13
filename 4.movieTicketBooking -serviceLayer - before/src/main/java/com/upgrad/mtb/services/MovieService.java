package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;

import java.util.List;

public interface MovieService {
    public Movie acceptMovieDetails(Movie movie);
    public Movie getMovieDetails(int id)   throws MovieDetailsNotFoundException;
    public boolean deleteMovie(int id)  throws MovieDetailsNotFoundException ;
    public List<Movie> getAllMoviesDetails();
}

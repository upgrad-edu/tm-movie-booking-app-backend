package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.exceptions.*;

import java.util.List;

public interface MovieService {
    public Movie acceptMovieDetails(Movie movie);
    public Movie getMovieDetails(int id)   throws MovieDetailsNotFoundException;
    public Movie updateMovieDetails(int movieId, Movie movie) throws MovieDetailsNotFoundException;
    public boolean deleteMovie(int id)  throws MovieDetailsNotFoundException ;
    public List<Movie> getAllMoviesDetails();
}

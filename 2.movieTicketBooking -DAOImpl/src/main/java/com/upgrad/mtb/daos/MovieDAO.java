package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Movie;

import java.util.List;

public interface MovieDAO {
    public Movie acceptMovieDetails(Movie movie);
    public Movie getMovieDetails(int movieId);
    public boolean deleteMovie(int movieId);
    public List<Movie> getAllMovieDetails();
}

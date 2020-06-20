package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Movie;

import java.util.List;

public interface MovieDAO {
    public Movie acceptMovieDetails(Movie movie);
    public Movie acceptMovieDetailsTransactional(Movie movie);
    public Movie getMovieDetails(int id);
    public boolean deleteMovie(int id);
    public boolean deleteMovieTransactional(int id);
    public List<Movie> getAllMovieDetails();
    public Movie getMovieDetailsByName(String name);
}

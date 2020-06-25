package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;

import java.util.List;

public interface MovieService {
    public Movie acceptMovieDetails(MovieDTO movieDTO) throws LanguageDetailsNotFoundException, StatusDetailsNotFoundException;
    public Movie getMovieDetails(int id)   throws MovieDetailsNotFoundException;
    public Movie updateMovieDetails(int id, MovieDTO movieDTO) throws MovieDetailsNotFoundException, LanguageDetailsNotFoundException, StatusDetailsNotFoundException;
    public Movie updateMovieDetails(Movie movie);
    public boolean deleteMovie(int id)  throws MovieDetailsNotFoundException ;
    public List<Movie> getAllMoviesDetails();
}

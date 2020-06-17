package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDAO movieDAO ;

    @Override
    public Movie acceptMovieDetails(Movie movie) {
        return movieDAO.save(movie);
    }

    @Override
    public Movie getMovieDetails(int id) throws MovieDetailsNotFoundException {
        Movie movie = movieDAO.findById(id).orElseThrow(
                ()->  new MovieDetailsNotFoundException("movie not found for " + id));
        return movie;
    }

    @Override
    public Movie getMovieDetailsByMovieName(String movieName) throws MovieDetailsNotFoundException {
        Movie myMovie = movieDAO.findByName(movieName);
        if(myMovie == null)
            throw new MovieDetailsNotFoundException("Details not found for :" + movieName);
        else
            return myMovie;
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

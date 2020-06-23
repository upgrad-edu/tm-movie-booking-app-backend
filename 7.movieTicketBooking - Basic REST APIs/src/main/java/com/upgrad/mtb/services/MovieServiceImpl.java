package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {
    @Autowired
    @Qualifier("movieDAO")
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
    public Movie updateMovieDetails(int id, Movie movie) throws MovieDetailsNotFoundException {
        Movie initialMovie = getMovieDetails(id);
        System.out.println("Initial movie details : " + initialMovie.toString());
        initialMovie.setReleaseDate(movie.getReleaseDate());
        initialMovie.setStatus(movie.getStatus());
        initialMovie.setLanguage(movie.getLanguage());
        initialMovie.setDescription(movie.getDescription());
        initialMovie.setDuration(movie.getDuration());
        initialMovie.setTrailerURL(movie.getTrailerURL());
        initialMovie.setCoverPhotoURL(movie.getCoverPhotoURL());
        initialMovie.setName(movie.getName());
        initialMovie.setTheatres(movie.getTheatres());
        acceptMovieDetails(initialMovie);
        System.out.println("New movie details :" + getMovieDetails(id).toString());
        return initialMovie;
    }

   /* @Override
    public Movie getMovieDetailsByMovieName(String movieName) throws MovieDetailsNotFoundException {
        Movie myMovie = movieDAO.findByName(movieName);
        if(myMovie == null)
            throw new MovieDetailsNotFoundException("Details not found for :" + movieName);
        else
            return myMovie;
    }*/

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

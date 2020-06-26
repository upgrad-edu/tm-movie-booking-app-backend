package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.daos.LanguageDAO;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.daos.StatusDAO;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {

    public MovieServiceImpl(){

    }

    @Autowired
    private MovieDAO movieDAO ;

    @Autowired
    LanguageDAO languageDAO;

    @Autowired
    StatusDAO statusDAO;

    @Override
    public Movie acceptMovieDetails(Movie movie, String languageName, int status) throws StatusDetailsNotFoundException, LanguageDetailsNotFoundException {

        Optional<Language> optionalLanguage = languageDAO.findByLanguage(languageName);
        Language foundLanguage = optionalLanguage.orElseThrow(()->new LanguageDetailsNotFoundException("Language Not Found "));
        Optional<Status> optionalStatus= statusDAO.findById(status);
        Status foundStatus = optionalStatus.orElseThrow(()->new StatusDetailsNotFoundException("Status Details are not found with ID "+ status));


        movie.setStatus(foundStatus);
        movie.setLanguage(foundLanguage);
        return movieDAO.save(movie);
    }
    @Override
    public Movie getMovieDetails(int id) throws MovieDetailsNotFoundException {
        Movie movie = movieDAO.findById(id).orElseThrow(
                ()->  new MovieDetailsNotFoundException("movie not found for " + id));
        return movie;
    }

    @Override
    public boolean removeMovie (int id) throws MovieDetailsNotFoundException {
        Movie movie = getMovieDetails(id);
        movieDAO.delete(movie);
        return true;
    }

    @Override
    public List<Movie> getAllMoviesDetails() {
        List<Movie> listOFMovies = movieDAO.findAll();
        return  listOFMovies;
    }

    @Override
    public List<Movie> searchMovieDetailsByReleaseDate(String releaseDate) {
        return null;
    }

    @Override
    public Movie searchMovieDetailsByName(String name) {

        return null;
    }

    @Override
    public Movie movieTheatreDetails(String name) {
        return null;
    }

    @Override
    public List<Movie> getAllMoviesDetailsByLanguage(String language) throws LanguageDetailsNotFoundException {
        Optional<Language> optionalLanguage = languageDAO.findByLanguage(language);
        Language foundLanguage = optionalLanguage.orElseThrow(()->new LanguageDetailsNotFoundException("Language Not Found "));

        List<Movie> movies =foundLanguage.getMovies();
        return movies;
    }
}

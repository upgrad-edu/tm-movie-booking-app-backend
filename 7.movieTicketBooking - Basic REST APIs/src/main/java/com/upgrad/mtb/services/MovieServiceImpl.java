package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.daos.MovieDAO;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    @Override
    public Movie acceptMovieDetails(MovieDTO movieDTO) throws LanguageDetailsNotFoundException, StatusDetailsNotFoundException {
        Movie newMovie = new Movie();
        newMovie.setName(movieDTO.getName());
        newMovie.setCoverPhotoURL(movieDTO.getCoverURL());
        newMovie.setTrailerURL(movieDTO.getTrailerURL());
        newMovie.setDuration(movieDTO.getDuration());
        newMovie.setDescription(movieDTO.getDescription());
        newMovie.setReleaseDate(movieDTO.getReleaseDate());
        newMovie.setTheatres(movieDTO.getTheatres());
        newMovie.setLanguage(languageService.getLanguageDetails(movieDTO.getLanguageId()));
        newMovie.setStatus(statusService.getStatusDetails(movieDTO.getStatusId()));
        return movieDAO.save(newMovie);
    }

    @Override
    public Movie getMovieDetails(int id) throws MovieDetailsNotFoundException {
        Movie movie = movieDAO.findById(id).orElseThrow(
                ()->  new MovieDetailsNotFoundException("movie not found for " + id));
        return movie;
    }

    @Override
    public Movie updateMovieDetails(int id, MovieDTO movieDTO) throws MovieDetailsNotFoundException, LanguageDetailsNotFoundException, StatusDetailsNotFoundException {
        Movie initialMovie = getMovieDetails(id);
        System.out.println("Initial movie details : " + initialMovie.toString());
        initialMovie.setName(movieDTO.getName());
        initialMovie.setCoverPhotoURL(movieDTO.getCoverURL());
        initialMovie.setTrailerURL(movieDTO.getTrailerURL());
        initialMovie.setDuration(movieDTO.getDuration());
        initialMovie.setDescription(movieDTO.getDescription());
        initialMovie.setReleaseDate(movieDTO.getReleaseDate());
        initialMovie.setTheatres(movieDTO.getTheatres());
        initialMovie.setLanguage(languageService.getLanguageDetails(movieDTO.getLanguageId()));
        initialMovie.setStatus(statusService.getStatusDetails(movieDTO.getStatusId()));
        return movieDAO.save(initialMovie);
    }

    public Movie updateMovieDetails(Movie movie){
        return movieDAO.save(movie);
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

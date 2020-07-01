package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.dto.ResponseMovieDTO;
import com.upgrad.mtb.exceptions.*;
import com.upgrad.mtb.services.MovieService;
import com.upgrad.mtb.services.TheatreService;
import com.upgrad.mtb.validator.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @Autowired
    TheatreService theatreService;

    @Autowired
    MovieValidator movieValidator;

    @RequestMapping(value= {"/sayHelloMovie"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From MovieController", HttpStatus.OK);
    }

    //MOVIE CONTROLLER
    @PostMapping(value="/movies",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity newMovie(@RequestBody MovieDTO movieDTO) throws StatusDetailsNotFoundException, LanguageDetailsNotFoundException {
        ResponseEntity responseEntity = null;
        try {
            movieValidator.validateMovie(movieDTO);
            Movie responseMovie = movieService.acceptMovieDetails(movieDTO);
            ResponseMovieDTO newMovieDTO = new ResponseMovieDTO();
            newMovieDTO.setMovieId(responseMovie.getId());
            newMovieDTO.setName(responseMovie.getName());
            newMovieDTO.setReleaseDate(responseMovie.getReleaseDate());
            newMovieDTO.setCoverURL(responseMovie.getCoverPhotoURL());
            newMovieDTO.setTrailerURL(responseMovie.getTrailerURL());
            newMovieDTO.setDescription(responseMovie.getDescription());
            newMovieDTO.setDuration(responseMovie.getDuration());
            newMovieDTO.setStatusId(responseMovie.getStatus().getId());
            newMovieDTO.setLanguageId(responseMovie.getLanguage().getId());
           newMovieDTO.setTheatres(responseMovie.getTheatres());
            responseEntity = ResponseEntity.ok(newMovieDTO);
        } catch (APIException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity getMovieDetails(@PathVariable(name = "id") int id) throws MovieDetailsNotFoundException {
        Movie responseMovie = movieService.getMovieDetails(id);
        ResponseMovieDTO movieDTO = new ResponseMovieDTO();
        movieDTO.setMovieId(responseMovie.getId());
        movieDTO.setName(responseMovie.getName());
        movieDTO.setReleaseDate(responseMovie.getReleaseDate());
        movieDTO.setCoverURL(responseMovie.getCoverPhotoURL());
        movieDTO.setTrailerURL(responseMovie.getTrailerURL());
        movieDTO.setDescription(responseMovie.getDescription());
        movieDTO.setDuration(responseMovie.getDuration());
        movieDTO.setStatusId(responseMovie.getStatus().getId());
        movieDTO.setLanguageId(responseMovie.getLanguage().getId());
        movieDTO.setTheatres(responseMovie.getTheatres());
        return ResponseEntity.ok(movieDTO);
    }

    @PutMapping(value="/movies/{id}",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity updateMovieDetails(@PathVariable(name = "id") int id, @RequestBody MovieDTO movieDTO) throws MovieDetailsNotFoundException, StatusDetailsNotFoundException, LanguageDetailsNotFoundException {
        return  ResponseEntity.ok(movieService.updateMovieDetails(id,movieDTO));
    }

    @GetMapping(value="/movies",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity findMoviesByNumber(@RequestParam(defaultValue = "-1") int number) throws APIException {
        if(number>0){
                List<Movie> movies = movieService.getAllMoviesDetails();
                if(movies.size() < number)
                    throw new APIException("Invalid number of movies");
                else{
                    List<Movie> movieList = movies.subList(Math.max(movies.size() - number, 0), movies.size());
                    return ResponseEntity.ok(movieList);
                }
            }else {
                List<Movie> movies = movieService.getAllMoviesDetails();
                return ResponseEntity.ok(movies);
            }
    }


    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> removeMovieDetails(@PathVariable(name = "id") int id) throws MovieDetailsNotFoundException{
        movieService.deleteMovie(id);
        return new ResponseEntity<>("Movie details successfully removed ",HttpStatus.OK);
    }

    @GetMapping(value="/movies/{movieId}/theatres",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity findAllTheatresForMovie( @PathVariable("movieId") int movieId) throws MovieDetailsNotFoundException {
        Movie movie = movieService.getMovieDetails(movieId);
        return ResponseEntity.ok(movie.getTheatres());
    }

    @DeleteMapping(value="/movies/{movieId}/theatres/{theatreId}",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity removeTheatreForMovie(@PathVariable("movieId") int movieId , @PathVariable("theatreId") int theatreId) throws MovieDetailsNotFoundException, TheatreDetailsNotFoundException {
        Theatre theatre = theatreService.getTheatreDetails(theatreId);
        theatre.setMovie(null);
        Movie movie = movieService.getMovieDetails(movieId);
        List<Theatre> theatres = movie.getTheatres();
        theatres.remove(theatre);
        movie.setTheatres(theatres);
        movieService.updateMovieDetails(movie);
        return ResponseEntity.ok(theatreService.updateTheatreDetails(theatre));
    }


}

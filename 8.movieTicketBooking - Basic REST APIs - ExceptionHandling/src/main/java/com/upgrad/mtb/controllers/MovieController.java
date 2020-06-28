package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import com.upgrad.mtb.services.MovieService;
import com.upgrad.mtb.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @Autowired
    TheatreService theatreService;

    @RequestMapping(value= {"/sayHelloMovie"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From MovieController", HttpStatus.OK);
    }

    //MOVIE CONTROLLER
    @PostMapping(value="/movies",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity newMovie(@RequestBody MovieDTO movieDTO) throws StatusDetailsNotFoundException, LanguageDetailsNotFoundException {
       Movie movie  =   movieService.
               acceptMovieDetails(movieDTO);
       return ResponseEntity.ok(movie);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity getMovieDetails(@PathVariable(name = "id") int id) throws MovieDetailsNotFoundException {
        Movie movie = movieService.getMovieDetails(id);
        return ResponseEntity.ok(movie);
    }

    @PutMapping(value="/movies/{id}",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity updateMovieDetails(@PathVariable(name = "id") int id, @RequestBody MovieDTO movieDTO) throws MovieDetailsNotFoundException, StatusDetailsNotFoundException, LanguageDetailsNotFoundException {
        return  ResponseEntity.ok(movieService.updateMovieDetails(id,movieDTO));
    }

    @GetMapping(value="/movies",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity findAllMovies() {
        List<Movie> movies = movieService.getAllMoviesDetails();
        return  ResponseEntity.ok(movies);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> removeMovieDetails(@PathVariable(name = "id") int id) throws MovieDetailsNotFoundException{
        movieService.deleteMovie(id);
        return new ResponseEntity<>("Movie details successfully removed ",HttpStatus.OK);
    }

    @GetMapping(value="/movies/{movieId}/theatres",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity findAllTheatresForMovie(@PathVariable("movieId") int movieId) throws MovieDetailsNotFoundException {
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

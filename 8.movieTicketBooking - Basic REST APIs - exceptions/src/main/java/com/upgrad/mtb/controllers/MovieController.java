package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import com.upgrad.mtb.services.MovieService;
import com.upgrad.mtb.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Movie newMovie(@RequestBody Movie movie) {
       return  movieService.acceptMovieDetails(movie);
    }

    @GetMapping("/movies/{id}")
    public Movie getMovieDetails(@PathVariable(name = "id") int id) throws MovieDetailsNotFoundException {
        System.out.println(movieService.getMovieDetails(id));
        return movieService.getMovieDetails(id);
    }

    @PutMapping(value="/movies/{id}",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public Movie updateMovieDetails(@PathVariable(name = "id") int id, @RequestBody Movie movie) throws MovieDetailsNotFoundException {
        return  movieService.updateMovieDetails(id,movie);
    }

    @GetMapping(value="/movies",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<Movie> findAllMovies() {
        return movieService.getAllMoviesDetails();
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> removeMovieDetails(@PathVariable(name = "id") int id) throws MovieDetailsNotFoundException{
        movieService.deleteMovie(id);
        return new ResponseEntity<>("Movie details successfully removed ",HttpStatus.OK);
    }

    @GetMapping(value="/movies/{movieId}/theatres",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<Theatre> findAllTheatresForMovie(@PathVariable("movieId") int movieId) throws MovieDetailsNotFoundException {
        Movie movie = getMovieDetails(movieId);
        return movie.getTheatres();
    }

    @DeleteMapping(value="/movies/{movieId}/theatres/{theatreId}",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public Theatre removeTheatreForMovie(@PathVariable("movieId") int movieId , @PathVariable("theatreId") int theatreId) throws MovieDetailsNotFoundException, TheatreDetailsNotFoundException {
        Theatre theatre = theatreService.getTheatreDetails(theatreId);
        theatre.setMovie(null);
        return  theatreService.updateTheatreDetails(theatreId, theatre);
    }


}

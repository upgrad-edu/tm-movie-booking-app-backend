package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {
    @Autowired
    MovieService movieService;

    @RequestMapping(value= {"/sayHelloMovie"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From MovieController", HttpStatus.OK);
    }

    //MOVIE CONTROLLER
    @PostMapping(value="/newMovie",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    void newMovie(@RequestBody Movie movie) {
        movieService.acceptMovieDetails(movie);
    }

    @GetMapping("/getMovieDetails")
    public Movie getMovieDetails(@RequestParam int id) throws MovieDetailsNotFoundException {
        System.out.println(movieService.getMovieDetails(id));
        return movieService.getMovieDetails(id);
    }

    @RequestMapping(value="/getAllMovieDetails",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<Movie> findAllMovies() {
        return movieService.getAllMoviesDetails();
    }

    @DeleteMapping("/deleteMovie")
    public ResponseEntity<String> removeMovieDetails(@RequestParam int id) throws MovieDetailsNotFoundException{
        movieService.deleteMovie(id);
        return new ResponseEntity<>("Movie details successfully removed ",HttpStatus.OK);
    }
}

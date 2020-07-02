package com.upgrad.mtb.controllers;

import com.upgrad.mtb.MovieTicketBookingApplication;
import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.exceptions.MovieDetailsNotFoundException;
import com.upgrad.mtb.services.MovieService;
import com.upgrad.mtb.services.MovieServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieTicketBookingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    MovieServiceImpl movieService;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void newMovieTest() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setName("Dhoom");
        movieDTO.setCoverURL("CoverURL");
        movieDTO.setTrailerURL("TrailerURL");
        movieDTO.setDuration(180);
        movieDTO.setReleaseDate(new Date("10/10/20"));
        movieDTO.setDescription("description");
        movieDTO.setTheatres(new ArrayList<Theatre>());
        movieDTO.setStatusId(3);
        movieDTO.setLanguageId(7);
        ResponseEntity response = restTemplate.postForEntity(getRootUrl() + "/movie_app/v1/movies", movieDTO, MovieDTO.class);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void getMovieDetails() throws MovieDetailsNotFoundException {
        int id = 13;
        Mockito.when(movieService.deleteMovie(Mockito.anyInt())).thenReturn(true);
        restTemplate.delete(getRootUrl() + "/movie_app/v1/movies/13", MovieDTO.class);
        Mockito.verify(movieService).deleteMovie(id);
    }

    @Test
    public void removeMovieDetailsTest() throws MovieDetailsNotFoundException {

        Mockito.when(movieService.deleteMovie(Mockito.anyInt())).thenReturn(true);
        ResponseEntity response = restTemplate.getForEntity(getRootUrl() + "/movie_app/v1/movies/13", MovieDTO.class);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void getAllMovieDetails() throws MovieDetailsNotFoundException {
        Movie movie1 = new Movie("Dhoom1", "racing movie",new Date("10/20/2020"),
                180,"coverPhotoURL","trailerURL",
                new Language("Hindi"), new Status("Upcoming"));
        Movie movie2 = new Movie("Dhoom2", "racing movie",new Date("10/20/2020"),
                180,"coverPhotoURL","trailerURL",
                new Language("Hindi"), new Status("Upcoming"));
        Movie movie3 = new Movie("Dhoom3", "racing movie",new Date("10/20/2020"),
                180,"coverPhotoURL","trailerURL",
                new Language("Hindi"), new Status("Upcoming"));
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        Mockito.when(movieService.getAllMoviesDetails()).thenReturn(movieList);
        ResponseEntity response = restTemplate.getForEntity(getRootUrl() + "/movie_app/v1/movies/", MovieDTO.class);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
    }







}

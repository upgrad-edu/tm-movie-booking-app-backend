package com.upgrad.mtb.controllers;

import com.upgrad.mtb.MovieTicketBookingApplication;
import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.dto.MovieDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieTicketBookingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllersTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void acceptMovieDetails() {
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

}

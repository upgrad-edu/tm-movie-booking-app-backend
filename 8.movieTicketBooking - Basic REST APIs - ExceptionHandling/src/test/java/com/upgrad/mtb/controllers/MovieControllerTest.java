package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.services.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    Movie movie = new Movie("Dhoom", "racing movie",new Date("10/20/2020"),
                    180,"coverPhotoURL","trailerURL",
                    new Language("Hindi"), new Status("Upcoming"));

    @Test
    public void getMovieDetailsTest() throws Exception {
        ResponseEntity responseEntity = ResponseEntity.ok(movie);
        Mockito.when(movieService.getMovieDetails(Mockito.anyInt())).thenReturn(movie);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/movie_app/v1/movies/13").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        // {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

        /*JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);*/
    }
}

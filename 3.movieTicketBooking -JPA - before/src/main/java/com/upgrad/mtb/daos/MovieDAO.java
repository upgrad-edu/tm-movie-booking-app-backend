package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("movieDAO")
public interface MovieDAO extends JpaRepository<Movie, Integer> {

   /* @Query("From Movie m Where m.name = :name")
    Movie getMovieDetails(@Param("name") String name);

    Movie findByName(String name);
    List<Movie> findByDuration(int duration);

    List<Movie> findByDurationBetween(int duration1, int duration2);*/
}

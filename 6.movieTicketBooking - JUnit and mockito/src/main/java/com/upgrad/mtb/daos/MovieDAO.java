package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("movieDAO")
public interface MovieDAO extends JpaRepository<Movie, Integer> {
    @Query(value = "From Movie m Where m.name = :name")                 //Spring Data
    List<Movie> getMovieDetailsName(@Param("name") String name);

    @Query(value = "From Movie m Where m.duration = :duration")                 //Spring Data
    List<Movie> getMovieDetailsDuration(@Param("duration") int  duration);

    List<Movie>  findByName(String name);

    List<Movie>  findByDuration(int duration);

    List<Movie>  findByDurationBetween(int durationFrom, int durationTo);

    List<Movie>  findByNameLike(String like);

    List<Movie>  findByDurationIn(List<Integer>durationValues);

    List<Movie>  findByDurationAndName(int duration, String name);

}

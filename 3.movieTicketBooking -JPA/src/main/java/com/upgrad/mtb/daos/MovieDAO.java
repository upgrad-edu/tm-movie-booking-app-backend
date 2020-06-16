package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("movieDAO")
public interface MovieDAO extends JpaRepository<Movie, Integer> {
    Movie findByName(String movieName);
}

package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDAO extends JpaRepository<Movie, Integer> {
}

package com.upgrad.mtb.DAO;

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieDAO extends JpaRepository<Movie, Integer> {
}

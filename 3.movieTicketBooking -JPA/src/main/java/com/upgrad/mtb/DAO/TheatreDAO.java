package com.upgrad.mtb.DAO;

import com.upgrad.mtb.beans.Customer;
import com.upgrad.mtb.beans.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreDAO extends JpaRepository<Theatre, Integer> {
}

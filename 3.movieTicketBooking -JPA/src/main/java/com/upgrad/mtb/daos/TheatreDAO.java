package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreDAO extends JpaRepository<Theatre, Integer> {
}

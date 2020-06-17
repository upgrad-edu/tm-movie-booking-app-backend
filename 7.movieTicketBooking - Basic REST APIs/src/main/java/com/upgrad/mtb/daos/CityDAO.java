package com.upgrad.mtb.daos;

import com.upgrad.mtb.beans.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("cityDAO")
public interface CityDAO extends JpaRepository<City , Integer> {
    City findDistinctByCity(String city);
}

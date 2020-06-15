package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.exceptions.CityDetailsNotFoundException;

import java.util.List;

public interface CityService {
    public City acceptCityDetails(City city);
    public City getCityDetails(int id) throws CityDetailsNotFoundException;
    public boolean deleteCity(int id) throws CityDetailsNotFoundException;
    public List<City> getAllCityDetails();
}

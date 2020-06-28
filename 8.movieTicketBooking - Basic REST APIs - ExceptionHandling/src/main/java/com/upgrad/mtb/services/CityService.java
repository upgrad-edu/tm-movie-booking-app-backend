package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.exceptions.CityDetailsNotFoundException;

import java.util.List;

public interface CityService {
    public City acceptCityDetails(City city);
    public City updateCityDetails(int initialCityId, City newCity);
    public City getCityDetails(int id) throws CityDetailsNotFoundException;
    public List<City> getMultipleCityDetails(int cityId1, int cityId2 , int cityId3) throws CityDetailsNotFoundException;
    public City getCityDetailsByCityName(String cityName) throws CityDetailsNotFoundException;
    public boolean deleteCity(int id) throws CityDetailsNotFoundException;
    public List<City> getAllCityDetails();
}

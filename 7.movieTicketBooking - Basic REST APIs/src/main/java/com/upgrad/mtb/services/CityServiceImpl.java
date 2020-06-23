package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.daos.CityDAO;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CityDetailsNotFoundException;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("cityService")
public class CityServiceImpl implements CityService {
    @Qualifier("cityDAO")
    @Autowired
    CityDAO cityDAO;

    @Override
    public City acceptCityDetails(City city) {
        return cityDAO.save(city);
    }

    @Override
    public City updateCityDetails(int initialCityId, City newCity) {
        City initialCity = getCityDetails(initialCityId);
        System.out.println("Initial city details : " + initialCity.toString());
        initialCity.setCity(newCity.getCity());
        acceptCityDetails(initialCity);
        System.out.println("New city details :" + getCityDetails(initialCityId).toString());
        return initialCity;
    }

    @Override
    public City getCityDetails(int id) throws CityDetailsNotFoundException {
        return cityDAO.findById(id).orElseThrow(
                ()->  new CityDetailsNotFoundException("Details not found for id : " + id));
    }

    @Override
    public List<City> getMultipleCityDetails(int cityId1, int cityId2, int cityId3) throws CityDetailsNotFoundException {
        List<City> cities = new ArrayList<>();
        cities.add(cityDAO.findById(cityId1).orElseThrow(
                ()->  new CityDetailsNotFoundException("Details not found for id : " + cityId1)));
        cities.add(cityDAO.findById(cityId2).orElseThrow(
                ()->  new CityDetailsNotFoundException("Details not found for id : " + cityId2)));
        cities.add(cityDAO.findById(cityId3).orElseThrow(
                ()->  new CityDetailsNotFoundException("Details not found for id : " + cityId3)));
        return cities;
    }

    @Override
    public City getCityDetailsByCityName(String cityName) throws CityDetailsNotFoundException {
        City myCity = cityDAO.findDistinctByCity(cityName);
        if(myCity == null)
            throw new CityDetailsNotFoundException("Details not found for :" +  cityName);
        else
            return myCity;
    }

    @Override
    public boolean deleteCity(int id) throws CityDetailsNotFoundException {
        City city = getCityDetails(id);
        cityDAO.delete(city);
        return true;
    }

    @Override
    public List<City> getAllCityDetails() {
       return cityDAO.findAll();
    }
}

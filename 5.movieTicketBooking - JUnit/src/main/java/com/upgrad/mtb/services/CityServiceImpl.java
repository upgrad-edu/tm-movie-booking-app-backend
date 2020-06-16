package com.upgrad.mtb.services;

import com.upgrad.mtb.beans.Booking;
import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.daos.CityDAO;
import com.upgrad.mtb.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mtb.exceptions.CityDetailsNotFoundException;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("cityService")
public class CityServiceImpl implements CityService {
    @Autowired
    CityDAO cityDAO;

    @Override
    public City acceptCityDetails(City city) {
        return cityDAO.save(city);
    }

    @Override
    public City getCityDetails(int id) throws CityDetailsNotFoundException {
        return cityDAO.findById(id).orElseThrow(
                ()->  new CityDetailsNotFoundException("Details not found for id : " + id));
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

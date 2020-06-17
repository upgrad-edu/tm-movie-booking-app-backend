package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.exceptions.CityDetailsNotFoundException;
import com.upgrad.mtb.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CityController {
    @Autowired
    CityService cityService;

    @RequestMapping(value= {"/sayHelloCity"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From CityController", HttpStatus.OK);
    }

    //CITY CONTROLLER
    @PostMapping(value="/newCity",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    void newCity(@RequestBody City city) {
        cityService.acceptCityDetails(city);
    }

    @GetMapping("/getCityDetails")
    public City getCityDetails(@RequestParam int id) throws CityDetailsNotFoundException {
        System.out.println(cityService.getCityDetails(id));
        return cityService.getCityDetails(id);
    }

    @RequestMapping(value="/getAllCityDetails",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<City> findAllCities() {
        return cityService.getAllCityDetails();
    }

    @DeleteMapping("/deleteCity")
    public ResponseEntity<String> removeCityDetails(@RequestParam int id) throws CityDetailsNotFoundException{
        cityService.deleteCity(id);
        return new ResponseEntity<>("City details successfully removed ",HttpStatus.OK);
    }
}

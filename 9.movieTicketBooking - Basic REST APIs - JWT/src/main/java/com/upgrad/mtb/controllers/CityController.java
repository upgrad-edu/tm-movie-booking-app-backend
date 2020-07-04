package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.City;
import com.upgrad.mtb.exceptions.CityDetailsNotFoundException;
import com.upgrad.mtb.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @RequestMapping(value= {"/sayHelloCity"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From CityController", HttpStatus.OK);
    }

    //CITY CONTROLLER
    @PostMapping(value="/cities",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public City newCity(@RequestBody City city) {
        System.out.println(city.toString());
        return cityService.acceptCityDetails(city);
    }

    @GetMapping("/cities/{id}")
    //@ResponseBody
    public City getCityDetails(@PathVariable(name = "id") int id) throws CityDetailsNotFoundException {
        System.out.println(cityService.getCityDetails(id));
        return cityService.getCityDetails(id);
    }

  /*  @GetMapping("/cities?cityId1={id1}&cityId2={id2}&cityId3={id3}")
    public List<City> getMultipleCityDetails(@PathVariable(name = "id1") int id1, @PathVariable(name = "id2") int id2, @PathVariable(name = "id3") int id3) throws CityDetailsNotFoundException {
        return cityService.getMultipleCityDetails(id1,id2,id3);
    }*/

    @GetMapping(value="/cities",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<City> findAllCities() {
        return cityService.getAllCityDetails();
    }

    @PutMapping("/cities/{id}")
    public City updateCityDetails( @PathVariable(name = "id") int id , @RequestBody City city){
        return cityService.updateCityDetails(id, city);
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<String> removeCityDetails(@PathVariable(name = "id")  int id) throws CityDetailsNotFoundException{
        cityService.deleteCity(id);
        return new ResponseEntity<>("City details successfully removed ",HttpStatus.OK);
    }
}

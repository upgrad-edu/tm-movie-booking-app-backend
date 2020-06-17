package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Theatre;
import com.upgrad.mtb.exceptions.TheatreDetailsNotFoundException;
import com.upgrad.mtb.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TheatreController {
    @Autowired
    TheatreService statusService;

    @RequestMapping(value= {"/sayHelloTheatre"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From TheatreController", HttpStatus.OK);
    }

    //STATUS CONTROLLER
    @PostMapping(value="/newTheatre",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    void newTheatre(@RequestBody Theatre status) {
        statusService.acceptTheatreDetails(status);
    }

    @GetMapping("/getTheatreDetails")
    public Theatre getTheatreDetails(@RequestParam int id) throws TheatreDetailsNotFoundException {
        System.out.println(statusService.getTheatreDetails(id));
        return statusService.getTheatreDetails(id);
    }

    @RequestMapping(value="/getAllTheatreDetails",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<Theatre> findAllTheatre() {
        return statusService.getAllTheatreDetails();
    }

    @DeleteMapping("/deleteTheatre")
    public ResponseEntity<String> removeTheatreDetails(@RequestParam int id) throws TheatreDetailsNotFoundException{
        statusService.deleteTheatre(id);
        return new ResponseEntity<>("Theatre details successfully removed ",HttpStatus.OK);
    }
}

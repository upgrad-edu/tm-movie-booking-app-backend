package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Status;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;
import com.upgrad.mtb.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StatusController {
    @Autowired
    StatusService statusService;

    //STATUS CONTROLLER
    @RequestMapping(value= {"/sayHelloStatus"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From StatusController", HttpStatus.OK);
    }

    //STATUS CONTROLLER
    @PostMapping(value="/statuses",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public Status newStatus(@RequestHeader(value = "X-Access-Token") String accessToken , @RequestBody Status status) {
       return statusService.acceptStatusDetails(status);
    }

    @GetMapping("/statuses/{id}")
    public Status getStatusDetails(@RequestHeader(value = "X-Access-Token") String accessToken , @PathVariable("id") int id) throws StatusDetailsNotFoundException {
        System.out.println(statusService.getStatusDetails(id));
        return statusService.getStatusDetails(id);
    }

    @GetMapping(value="/statuses",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<Status> findAllStatus(@RequestHeader(value = "X-Access-Token") String accessToken) {
        return statusService.getAllStatusDetails();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeStatusDetails(@RequestHeader(value = "X-Access-Token") String accessToken , @PathVariable("id") int id) throws StatusDetailsNotFoundException{
        statusService.deleteStatus(id);
        return new ResponseEntity<>("Status details successfully removed ",HttpStatus.OK);
    }
}

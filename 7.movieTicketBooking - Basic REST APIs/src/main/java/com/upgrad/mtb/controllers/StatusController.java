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
    @PostMapping(value="/newStatus",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    void newStatus(@RequestBody Status status) {
        statusService.acceptStatusDetails(status);
    }

    @GetMapping("/getStatusDetails")
    public Status getStatusDetails(@RequestParam int id) throws StatusDetailsNotFoundException {
        System.out.println(statusService.getStatusDetails(id));
        return statusService.getStatusDetails(id);
    }

    @RequestMapping(value="/getAllStatusDetails",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<Status> findAllStatus() {
        return statusService.getAllStatusDetails();
    }

    @DeleteMapping("/deleteStatus")
    public ResponseEntity<String> removeStatusDetails(@RequestParam int id) throws StatusDetailsNotFoundException{
        statusService.deleteStatus(id);
        return new ResponseEntity<>("Status details successfully removed ",HttpStatus.OK);
    }
}

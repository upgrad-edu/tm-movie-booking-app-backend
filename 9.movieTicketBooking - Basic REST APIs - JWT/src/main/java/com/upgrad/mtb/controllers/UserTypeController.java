package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.exceptions.UserTypeDetailsNotFoundException;
import com.upgrad.mtb.services.UserTypeService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserTypeController {
    @Autowired
    UserTypeService userTypeService;

    //USERTYPE CONTROLLER
    @RequestMapping(value= {"/sayHelloUserType"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From UserTypeController", HttpStatus.OK);
    }
    
    @PostMapping(value="/usertypes",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public UserType newUserType(@RequestBody UserType userType) {
        return  userTypeService.acceptUserTypeDetails(userType);
    }

    @GetMapping("/usertypes/{id}")
    public UserType getUserTypeDetails(@PathVariable(name = "id") int id) throws UserTypeDetailsNotFoundException {
        System.out.println(userTypeService.getUserTypeDetails(id));
        return userTypeService.getUserTypeDetails(id);
    }

    @GetMapping(value="/usertypes",produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<UserType> findAllUserType() {
        return userTypeService.getAllUserTypeDetails();
    }

    @DeleteMapping("/usertypes/{id}")
    public ResponseEntity<String> removeUserTypeDetails(@PathVariable(name = "id") int id) throws UserTypeDetailsNotFoundException{
        userTypeService.deleteUserType(id);
        return new ResponseEntity<>("UserType details successfully removed ",HttpStatus.OK);
    }
}

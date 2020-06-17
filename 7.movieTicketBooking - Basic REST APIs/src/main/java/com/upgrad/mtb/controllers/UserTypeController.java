package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.UserType;
import com.upgrad.mtb.exceptions.UserTypeDetailsNotFoundException;
import com.upgrad.mtb.services.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserTypeController {
    @Autowired
    UserTypeService userTypeService;

    //USERTYPE CONTROLLER
    @RequestMapping(value= {"/sayHelloUserType"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From UserTypeController", HttpStatus.OK);
    }
    
    @PostMapping(value="/newUserType",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    void newUserType(@RequestBody UserType userType) {
        userTypeService.acceptUserTypeDetails(userType);
    }

    @GetMapping("/getUserTypeDetails")
    public UserType getUserTypeDetails(@RequestParam int id) throws UserTypeDetailsNotFoundException {
        System.out.println(userTypeService.getUserTypeDetails(id));
        return userTypeService.getUserTypeDetails(id);
    }

    @RequestMapping(value="/getAllUserTypeDetails",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<UserType> findAllUserType() {
        return userTypeService.getAllUserTypeDetails();
    }

    @DeleteMapping("/deleteUserType")
    public ResponseEntity<String> removeUserTypeDetails(@RequestParam int id) throws UserTypeDetailsNotFoundException{
        userTypeService.deleteUserType(id);
        return new ResponseEntity<>("UserType details successfully removed ",HttpStatus.OK);
    }
}

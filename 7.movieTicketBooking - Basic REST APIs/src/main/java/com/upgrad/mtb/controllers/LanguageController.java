package com.upgrad.mtb.controllers;

import com.upgrad.mtb.beans.Language;
import com.upgrad.mtb.exceptions.LanguageDetailsNotFoundException;
import com.upgrad.mtb.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @RequestMapping(value= {"/sayHelloLanguage"},method= RequestMethod.GET)
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<String>("Hello World To All From LanguageController", HttpStatus.OK);
    }

    //LANGUAGE CONTROLLER
    @PostMapping(value="/newLanguage",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    void newLanguage(@RequestBody Language language) {
        languageService.acceptLanguageDetails(language);
    }

    @GetMapping("/getLanguageDetails")
    public Language getLanguageDetails(@RequestParam int id) throws LanguageDetailsNotFoundException {
        System.out.println(languageService.getLanguageDetails(id));
        return languageService.getLanguageDetails(id);
    }

    @RequestMapping(value="/getAllLanguageDetails",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public List<Language> findAllLanguages() {
        return languageService.getAllLanguageDetails();
    }

    @DeleteMapping("/deleteLanguage")
    public ResponseEntity<String> removeLanguageDetails(@RequestParam int id) throws LanguageDetailsNotFoundException{
        languageService.deleteLanguage(id);
        return new ResponseEntity<>("Language details successfully removed ",HttpStatus.OK);
    }
}

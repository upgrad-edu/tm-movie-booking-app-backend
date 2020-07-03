package com.upgrad.mtb.validator;

import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.exceptions.APIException;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;
import com.upgrad.mtb.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MovieValidatorImpl implements MovieValidator {
    @Autowired
    StatusService statusService;

    public void validateMovie(MovieDTO movieDTO) throws APIException, ParseException, StatusDetailsNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(new Date());
        Date todaysDate = sdf.parse(dateString);
        String formatMovieDate = sdf.format(movieDTO.getReleaseDate());
        Date parsedMovieDate = sdf.parse(formatMovieDate);
        if(movieDTO.getName() == null || movieDTO.getName().length() <= 0)
            throw new APIException("Invalid movie name");
        if(movieDTO.getCoverURL() == null || movieDTO.getCoverURL().length() <=0)
            throw new APIException("Invalid cover url");
        if(movieDTO.getTrailerURL() == null || movieDTO.getTrailerURL().length() <=0)
            throw new APIException("Invalid trailer url");
        if(movieDTO.getDescription() == null || movieDTO.getDescription().length() <=0)
            throw new APIException("Invalid  description");
        if(movieDTO.getDuration() <= 25 || movieDTO.getDuration() > 240)
            throw new APIException("Invalid duration");
        if(movieDTO.getLanguageId() <= 0)
            throw new APIException("Invalid language");
        if(movieDTO.getStatusId() <= 0)
            throw new APIException("Invalid status");
        if(statusService.getStatusDetails(movieDTO.getStatusId()).getStatus().equalsIgnoreCase("Released")){
            if(parsedMovieDate.compareTo(todaysDate) <0 ){
                throw new APIException("Invalid movie release date");
            }
        }
    }
}

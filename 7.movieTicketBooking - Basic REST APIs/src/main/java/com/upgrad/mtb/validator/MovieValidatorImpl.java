package com.upgrad.mtb.validator;

import com.upgrad.mtb.beans.Movie;
import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.exceptions.APIException;
import org.springframework.stereotype.Service;

@Service
public class MovieValidatorImpl implements MovieValidator {

    public void validateMovie(MovieDTO movieDTO) throws APIException {
        if(movieDTO.getName() == null || movieDTO.getName().length() <= 0)
            throw new APIException("Invalid movie name");
        if(movieDTO.getCoverURL() == null || movieDTO.getCoverURL().length() <=0)
            throw new APIException("Invalid cover url");
        if(movieDTO.getTrailerURL() == null || movieDTO.getTrailerURL().length() <=0)
            throw new APIException("Invalid trailer url");
        if(movieDTO.getDescription() == null || movieDTO.getDescription().length() <=0)
            throw new APIException("Invalid  description");
        if(movieDTO.getDuration() <= 25)
            throw new APIException("Invalid duration");
        if(movieDTO.getLanguageId() <= 0)
            throw new APIException("Invalid language");
        if(movieDTO.getStatusId() <= 0)
            throw new APIException("Invalid status");

    }
}

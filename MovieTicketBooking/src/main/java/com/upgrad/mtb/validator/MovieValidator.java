package com.upgrad.mtb.validator;

import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.exceptions.APIException;
import com.upgrad.mtb.exceptions.StatusDetailsNotFoundException;

import java.text.ParseException;

public interface MovieValidator {
    public void validateMovie(MovieDTO movieDTO) throws APIException, ParseException, StatusDetailsNotFoundException;
}

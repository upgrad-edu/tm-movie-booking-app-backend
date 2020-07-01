package com.upgrad.mtb.validator;

import com.upgrad.mtb.dto.MovieDTO;
import com.upgrad.mtb.exceptions.APIException;

public interface MovieValidator {
    public void validateMovie(MovieDTO movieDTO) throws APIException;
}

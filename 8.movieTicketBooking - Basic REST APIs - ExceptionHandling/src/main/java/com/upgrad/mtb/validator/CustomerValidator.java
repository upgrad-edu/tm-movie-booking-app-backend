package com.upgrad.mtb.validator;

import com.upgrad.mtb.dto.CustomerDTO;
import com.upgrad.mtb.dto.LoginDTO;
import com.upgrad.mtb.exceptions.APIException;

public interface CustomerValidator {
    public void validateCustomer(CustomerDTO customerDTO) throws APIException;
    public void validateuserLogin(LoginDTO loginDTO) throws APIException;
}

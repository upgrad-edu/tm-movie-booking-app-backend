package com.upgrad.mtb.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponse {
    private String errorMessage;
    private int statusCode;

    public  CustomResponse(){

    }

    public CustomResponse(String errorMessage, int statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }


}

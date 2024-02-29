package com.ACME.Student.marketplace.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AcmeUtils {
    private AcmeUtils() {

    }
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"}", httpStatus);
    }
}

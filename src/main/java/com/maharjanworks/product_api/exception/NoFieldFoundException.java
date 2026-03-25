package com.maharjanworks.product_api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoFieldFoundException extends RuntimeException{

    public NoFieldFoundException(String message) {
        super(message);
    }
}

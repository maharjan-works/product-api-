package com.maharjanworks.product_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CategoryNotFoundException extends  RuntimeException{

    public CategoryNotFoundException(String message) {
        super(message);
    }
}

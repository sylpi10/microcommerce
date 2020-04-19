package com.ecommerce.microcommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnfoundProductException extends Throwable {
    public UnfoundProductException(String s) {
        super(s);
    }
}

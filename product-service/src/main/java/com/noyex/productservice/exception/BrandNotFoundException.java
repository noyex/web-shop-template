package com.noyex.productservice.exception;

public class BrandNotFoundException extends RuntimeException{

    public BrandNotFoundException(String message) {
        super(message);
    }
}

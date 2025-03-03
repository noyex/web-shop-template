package com.noyex.productservice.exception;

public class ProductNameExistsException extends RuntimeException{

    public ProductNameExistsException(String message) {
        super(message);
    }
}

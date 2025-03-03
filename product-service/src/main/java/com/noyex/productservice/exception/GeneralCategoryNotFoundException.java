package com.noyex.productservice.exception;

public class GeneralCategoryNotFoundException extends RuntimeException {
    public GeneralCategoryNotFoundException(String message) {
        super(message);
    }
}

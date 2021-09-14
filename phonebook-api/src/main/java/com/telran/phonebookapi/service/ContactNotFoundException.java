package com.telran.phonebookapi.service;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

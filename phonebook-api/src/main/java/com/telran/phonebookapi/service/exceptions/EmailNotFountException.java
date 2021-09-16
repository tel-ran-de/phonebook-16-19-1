package com.telran.phonebookapi.service.exceptions;

public class EmailNotFountException extends RuntimeException {
    public EmailNotFountException(String message) {
        super(message);
    }
}

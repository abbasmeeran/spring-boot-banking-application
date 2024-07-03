package org.banking.exception;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException() {
        super("Invalid Date");
    }
}

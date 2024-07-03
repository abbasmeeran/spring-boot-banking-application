package org.banking.exception;

public class InSufficientBalanceException extends RuntimeException{
    public InSufficientBalanceException() {
        super("No sufficient balance in your account    ");
    }
}

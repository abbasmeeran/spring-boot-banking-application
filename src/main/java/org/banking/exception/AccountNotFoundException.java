package org.banking.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(Integer accountId) {
        super("Account with id " + accountId + " not found");
    }

}

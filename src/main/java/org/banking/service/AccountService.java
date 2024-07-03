package org.banking.service;

import org.banking.entity.Account;

public interface AccountService {

    Integer createAccount(Account account);

    void deposit(Integer accountNumber, Double amount);

    void withdraw(Integer accountNumber, Double amount);

    Double checkBalance(Integer accountNumber);

}

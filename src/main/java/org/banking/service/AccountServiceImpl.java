package org.banking.service;

import org.banking.entity.Account;
import org.banking.entity.Transactions;
import org.banking.exception.AccountNotFoundException;
import org.banking.exception.InSufficientBalanceException;
import org.banking.repository.AccountRepo;
import org.banking.repository.TransactionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    TransactionsRepo transactionsRepo;


    @Override
    public Integer createAccount(Account account) {
        accountRepo.save(account);
        return account.getAccountNumber();
    }

    @Override
    public void deposit(Integer accountNumber, Double amount) {
        Optional<Account> accountOptional = accountRepo.findById(accountNumber);
        if(accountOptional.isEmpty()) {
            throw new AccountNotFoundException(accountNumber);
        }
        Account account = accountOptional.get();
        account.setBalanceAmount(account.getBalanceAmount() + amount);
        transactionsRepo.save(getTransaction(account, amount));
        accountRepo.save(account);
    }

    @Override
    public void withdraw(Integer accountNumber, Double amount) {
        Optional<Account> accountOptional = accountRepo.findById(accountNumber);
        if(accountOptional.isEmpty()) {
            throw new AccountNotFoundException(accountNumber);
        }
        Account account = accountOptional.get();
        if(account.getBalanceAmount() < amount) {
            throw new InSufficientBalanceException();
        }
        account.setBalanceAmount(account.getBalanceAmount() - amount);
        transactionsRepo.save(getTransaction(account, amount));
        accountRepo.save(account);
    }

    @Override
    public Double checkBalance(Integer accountNumber) {
        Optional<Account> accountOptional = accountRepo.findById(accountNumber);
        if(accountOptional.isEmpty()) {
            throw new InSufficientBalanceException();
        }
        Account account = accountOptional.get();
        return account.getBalanceAmount();
    }

    private Transactions getTransaction(Account account, Double amount) {
        Transactions transactions = new Transactions();
        transactions.setAccount(account);
        transactions.setDeposit(amount);
        transactions.setBalance(account.getBalanceAmount());
        transactions.setTransactionDate(new Date());
        return transactions;
    }
}

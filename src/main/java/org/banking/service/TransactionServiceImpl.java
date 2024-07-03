package org.banking.service;

import org.apache.commons.lang3.time.DateUtils;
import org.banking.controller.BankingController;
import org.banking.entity.Account;
import org.banking.entity.Transactions;
import org.banking.exception.AccountNotFoundException;
import org.banking.exception.InvalidDateException;
import org.banking.repository.AccountRepo;
import org.banking.repository.TransactionsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    private static final Logger loger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionsRepo transactionsRepo;

    @Autowired
    AccountRepo accountRepo;

    @Override
    public List<Transactions> getTransactions(Integer accountNumber) {
        Optional<Account> accountOptional = accountRepo.findById(accountNumber);
        if(accountOptional.isEmpty()) {
            throw new AccountNotFoundException(accountNumber);
        }
        return transactionsRepo.getTransactionsByAccount(accountNumber);
    }

    @Override
    public List<Transactions> getTransactions(Integer accountNumber, String startDateStr, String endDateStr) {
        Optional<Account> accountOptional = accountRepo.findById(accountNumber);
        if(accountOptional.isEmpty()) {
            throw new AccountNotFoundException(accountNumber);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = DateUtils.addHours(dateFormat.parse(endDateStr), 24);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
        loger.info("getTransactions " + startDate + " " + endDate);
        return transactionsRepo.getTransactionsByAccountAndDate(accountNumber, startDate, endDate);
    }
}

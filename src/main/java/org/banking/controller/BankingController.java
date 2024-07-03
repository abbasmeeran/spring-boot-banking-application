package org.banking.controller;

import org.banking.dto.TransactionDTO;
import org.banking.entity.Account;
import org.banking.entity.Transactions;
import org.banking.service.AccountService;
import org.banking.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankingController {
    private static final Logger loger = LoggerFactory.getLogger(BankingController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/account")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<>("Account created successfully", HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody TransactionDTO transactionDTO) {
        accountService.deposit(transactionDTO.getAccountNumber(), transactionDTO.getAmount());
        return new ResponseEntity<>("Amount deposited successfully", HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody TransactionDTO transactionDTO) {
        accountService.withdraw(transactionDTO.getAccountNumber(), transactionDTO.getAmount());
        return new ResponseEntity<>("Amount withdrawn successfully", HttpStatus.OK);
    }

    @GetMapping("transactions/{accountNumber}")
    public ResponseEntity<List<Transactions>> viewTransactionsById(@PathVariable Integer accountNumber) {
        List<Transactions> transactions = transactionService.getTransactions(accountNumber);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("transactions/{accountNumber}/**")
    public ResponseEntity<List<Transactions>> viewTransactionsById(@PathVariable Integer accountNumber,
                                                                   @RequestParam String startDate,
                                                                   @RequestParam String endDate) {

        loger.info("viewTransactionsById "+ accountNumber + " " + startDate + " " + endDate );
        List<Transactions> transactions = transactionService.getTransactions(accountNumber, startDate, endDate);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}

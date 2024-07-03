package org.banking.service;

import org.banking.entity.Transactions;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    List<Transactions> getTransactions(Integer accountNumber);

    List<Transactions> getTransactions(Integer accountNumber, String startDate, String endDate);
}

package org.banking.repository;

import org.banking.entity.Account;
import org.banking.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions, Integer> {

    @Query(value = "select transactions from Transactions transactions where transactions.account.accountNumber = ?1")
    List<Transactions> getTransactionsByAccount(Integer accountNumber);

    @Query(value = "select transactions from Transactions transactions where transactions.account.accountNumber = ?1" +
            " and transactions.transactionDate between ?2 and ?3")
    List<Transactions> getTransactionsByAccountAndDate(Integer accountNumber, Date startDate, Date endDate);
}

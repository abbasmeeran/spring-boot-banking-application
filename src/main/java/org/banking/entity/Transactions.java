package org.banking.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tranId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "accountNumber")
    private Account account;

    @Column
    private Date transactionDate;

    @Column(length = 100)
    private String description;

    @Column
    private Double deposit;

    @Column
    private Double withdraw;

    @Column
    private Double balance;

    public Integer getTranId() {
        return tranId;
    }

    public void setTranId(Integer tranId) {
        this.tranId = tranId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Double withdraw) {
        this.withdraw = withdraw;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "tranId=" + tranId +
                ", account=" + account +
                ", transactionDate=" + transactionDate +
                ", description='" + description + '\'' +
                ", deposit=" + deposit +
                ", withdraw=" + withdraw +
                ", balance=" + balance +
                '}';
    }
}

package org.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingAppMain {
    public static void main(String[] args) {
        SpringApplication.run(BankingAppMain.class, args);
        System.out.println("Spring boot application started...");
    }
}
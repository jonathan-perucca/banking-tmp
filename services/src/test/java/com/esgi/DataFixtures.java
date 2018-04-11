package com.esgi;

import com.esgi.bank.BankAccount;
import com.esgi.crm.User;

import java.time.LocalDate;

public class DataFixtures {

    public static User user() {
        return new User(1, "fname", "lname", LocalDate.now().minusYears(25));
    }

    public static BankAccount bankAccount() {
        return new BankAccount(1, user(), 0);
    }
}

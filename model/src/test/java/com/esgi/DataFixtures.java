package com.esgi;

import com.esgi.crm.User;

import java.time.LocalDate;

public class DataFixtures {

    public static User user() {
        return new User(1, "fname", "lname", LocalDate.now().minusYears(25));
    }
}

package com.esgi.crm;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void should_valid_nominal() {
        User user = new User(1, "fname", "lname", LocalDate.now().minusYears(25));
        assertTrue(user.isValid());
    }

    @Test
    public void should_not_valid_firstname_is_null() {
        User user = new User(1, null, "lname", LocalDate.now().minusYears(25));
        assertFalse(user.isValid());
    }

    @Test
    public void should_not_valid_firstname_is_blank() {
        User user = new User(1, "", "lname", LocalDate.now().minusYears(25));
        assertFalse(user.isValid());
    }


    @Test
    public void should_not_valid_lastname_is_null() {
        User user = new User(1, "fname", null, LocalDate.now().minusYears(25));
        assertFalse(user.isValid());
    }

    @Test
    public void should_not_valid_lastname_is_blank() {
        User user = new User(1, "fname", "", LocalDate.now().minusYears(25));
        assertFalse(user.isValid());
    }

    @Test
    public void should_not_valid_birthdate_is_null() {
        User user = new User(1, "fname", "lname", null);
        assertFalse(user.isValid());
    }

    @Test
    public void should_not_valid_user_is_not_adult() {
        User user = new User(1, "fname", "lname", LocalDate.now().minusYears(7));
        assertFalse(user.isValid());
    }
}

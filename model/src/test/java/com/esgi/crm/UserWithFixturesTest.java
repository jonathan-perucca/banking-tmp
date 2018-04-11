package com.esgi.crm;

import com.esgi.DataFixtures;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Use a fixture to initialise tests
 */
public class UserWithFixturesTest {

    @Test
    public void should_valid_nominal() {
        User user = DataFixtures.user();
        assertTrue(user.isValid());
    }

    @Test
    public void should_not_valid_firstname_is_null() {
        User user = DataFixtures.user();
        user.setFirstname(null);

        assertFalse(user.isValid());
    }

    @Test
    public void should_not_valid_firstname_is_blank() {
        User user = DataFixtures.user();
        user.setFirstname("");

        assertFalse(user.isValid());
    }


    @Test
    public void should_not_valid_lastname_is_null() {
        User user = DataFixtures.user();
        user.setLastname(null);

        assertFalse(user.isValid());
    }

    @Test
    public void should_not_valid_lastname_is_blank() {
        User user = DataFixtures.user();
        user.setLastname("");

        assertFalse(user.isValid());
    }

    @Test
    public void should_not_valid_birthdate_is_null() {
        User user = DataFixtures.user();
        user.setBirthdate(null);

        assertFalse(user.isValid());
    }

    @Test
    public void should_not_valid_user_is_not_adult() {
        User user = DataFixtures.user();
        user.setBirthdate(LocalDate.now().minusYears(7));

        assertFalse(user.isValid());
    }
}

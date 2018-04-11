package com.esgi.bank;

import com.esgi.crm.User;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * setUp called before each test
 * tearDown call after each test
 * test name should be start with the key word "test" because of the "extends testCase"
 */
public class BankAccountTest extends TestCase {

    private BankAccount bankAccount;

    protected void setUp() throws Exception {
        super.setUp();
        this.bankAccount = BankAccount.builder()
                .owner(new User())
                .amount(0)
                .build();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        this.bankAccount = null;
    }

    @Test
    public void test_should_maximum_amount_reached() {
        this.bankAccount.setAmount(1000);
        assertTrue(bankAccount.maximumAmountReached());
    }

    @Test
    public void test_should_not_maximum_amount_reached() {
        this.bankAccount.setAmount(999);
        assertFalse(bankAccount.maximumAmountReached());
    }
}

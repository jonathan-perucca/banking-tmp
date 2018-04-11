package com.esgi.service.bank;

import com.esgi.bank.BankAccount;
import com.esgi.crm.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BankAccountServiceParameterizedTest {

    private final Integer initialBankAccountAmount;
    private final Integer amountToAdd;
    private final Integer newBankAccountAmount;
    private final Integer addedAmount;

    private BankAccountService sut = new BankAccountService(null);

    @Parameters(name = "initial bank account : {0}. Adding {1}. New bank account : {2}. Added amount is {3}")
    public static Collection<Object[]> params() {
        return Arrays.asList(
                new Object[]{0, 20, 20, 20},
                new Object[]{0, 200, 200, 200},
                new Object[]{0, 1000, 1000, 1000},
                new Object[]{0, 2000, 1000, 1000},
                new Object[]{200, 20, 220, 20},
                new Object[]{200, 200, 400, 200},
                new Object[]{200, 1000, 1000, 800},
                new Object[]{200, 2000, 1000, 800}
        );
    }

    public BankAccountServiceParameterizedTest(final Integer initialBankAccountAmount, final Integer amountToAdd, final Integer newBankAccountAmount, final Integer addedAmount) {
        this.initialBankAccountAmount = initialBankAccountAmount;
        this.amountToAdd = amountToAdd;
        this.newBankAccountAmount = newBankAccountAmount;
        this.addedAmount = addedAmount;
    }

    @Test
    public void should_credit_amount() throws Exception {
        BankAccount bankAccount = BankAccount.builder()
                .owner(new User())
                .amount(this.initialBankAccountAmount)
                .build();

        int addedAmount = sut.safeCredit(bankAccount, this.amountToAdd);

        assertEquals((int) this.addedAmount, addedAmount);
        assertEquals(this.newBankAccountAmount, bankAccount.getAmount());
    }

}

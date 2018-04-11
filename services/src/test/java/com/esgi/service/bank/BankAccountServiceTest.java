package com.esgi.service.bank;

import com.esgi.DataFixtures;
import com.esgi.bank.BankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountServiceTest {

    @InjectMocks
    private BankAccountService sut;

    @Test
    public void should_credit_nominal() throws Exception {
        BankAccount bankAccount = DataFixtures.bankAccount();
        sut.safeCredit(bankAccount, 100);
        assertThat(bankAccount.getAmount(), is(100));
    }

    @Test(expected = Exception.class)
    public void should_throw_exception_because_of_negative_amount() throws Exception {
        BankAccount bankAccount = DataFixtures.bankAccount();
        sut.safeCredit(bankAccount, -100);
    }
}

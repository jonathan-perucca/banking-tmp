package com.esgi.service.bank;

import com.esgi.repository.BankAccountRepository;
import com.esgi.bank.BankAccount;
import com.esgi.crm.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    /**
     * Create a new bank account
     *
     * @param user the bank account owner
     * @param initialAmount the initial amount, between 0 and 1000
     * @throws Exception
     */
    public void createBankAccount(User user, int initialAmount) throws Exception {
        if (user == null || !user.isValid()) {
            throw new Exception("Bad user: User is null or not valid");
        }

        BankAccount bankAccount = BankAccount.builder()
                .owner(user)
                .amount(initialAmount)
                .build();

        safeCredit(bankAccount, initialAmount);

    }

    /**
     * Credit the bank account
     *
     * @param bankAccount
     * @param amount between 0 and 1000
     * @return the amount added to bank account
     * @throws Exception
     */
    public int credit(BankAccount bankAccount, int amount) throws Exception {
        notNull(bankAccount, "Bank account must not be null");

        User owner = bankAccount.getOwner();
        if (owner == null || !owner.isValid()) {
            throw new Exception("Bad user: User is null or not valid");
        }

        int addedAmount = safeCredit(bankAccount, amount);
        bankAccountRepository.save(bankAccount);

        return addedAmount;
    }

    /**
     * Bank account must not be negative and must not be greater than 1000
     */
    protected int safeCredit(BankAccount bankAccount, int amount) throws Exception {
        notNull(bankAccount, "Bank account must not be null");

        if (amount < 0) {
            throw new Exception("Bad amount: bad amount parameter");
        }

        int initialAmount = bankAccount.getAmount();
        int newAmount = Math.min(initialAmount + amount, 1000);
        int addedAmount = amount + bankAccount.getAmount() <= 1000 ? amount : newAmount - initialAmount;

        bankAccount.setAmount(newAmount);
        return addedAmount;
    }
}

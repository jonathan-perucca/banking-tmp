package com.esgi.controller;

import com.esgi.crm.User;
import com.esgi.service.bank.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @RequestMapping(method = GET, value = "/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @RequestMapping(method = POST, value = "/bank/{amount}")
    @ResponseStatus(CREATED)
    public void createBankAccount(@PathVariable(value = "amount") final String amount) throws Exception {
        User user = User.builder()
                .firstname("firstname")
                .lastname("lastname")
                .birthdate(LocalDate.now().minusYears(33))
                .build();

        bankAccountService.createBankAccount(user, Integer.valueOf(amount));
    }
}

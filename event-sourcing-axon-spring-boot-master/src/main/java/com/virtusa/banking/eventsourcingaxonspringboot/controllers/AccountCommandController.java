package com.virtusa.banking.eventsourcingaxonspringboot.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import com.virtusa.banking.eventsourcingaxonspringboot.dto.commands.AccountCreateDTO;
import com.virtusa.banking.eventsourcingaxonspringboot.dto.commands.MoneyCreditDTO;
import com.virtusa.banking.eventsourcingaxonspringboot.dto.commands.MoneyDebitDTO;
import com.virtusa.banking.eventsourcingaxonspringboot.services.commands.AccountCommandService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/bank-accounts")
@Api(value = "Account Commands", description = "Account Commands Related Endpoints", tags = "Account Commands")
public class AccountCommandController {

    private final AccountCommandService accountCommandService;

    public AccountCommandController(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @PostMapping
    public CompletableFuture<String> createAccount(@RequestBody AccountCreateDTO accountCreateDTO){
        return accountCommandService.createAccount(accountCreateDTO);
    }

    @PutMapping(value = "/credits/{accountNumber}")
    public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountNumber") String accountNumber,
                                                          @RequestBody MoneyCreditDTO moneyCreditDTO){
        return accountCommandService.creditMoneyToAccount(accountNumber, moneyCreditDTO);
    }

    @PutMapping(value = "/debits/{accountNumber}")
    public CompletableFuture<String> debitMoneyFromAccount(@PathVariable(value = "accountNumber") String accountNumber,
                                                           @RequestBody MoneyDebitDTO moneyDebitDTO){
        return accountCommandService.debitMoneyFromAccount(accountNumber, moneyDebitDTO);
    }
}

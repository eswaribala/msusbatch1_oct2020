package com.virtusa.banking.eventsourcingaxonspringboot.services.commands;

import java.util.concurrent.CompletableFuture;

import com.virtusa.banking.eventsourcingaxonspringboot.dto.commands.AccountCreateDTO;
import com.virtusa.banking.eventsourcingaxonspringboot.dto.commands.MoneyCreditDTO;
import com.virtusa.banking.eventsourcingaxonspringboot.dto.commands.MoneyDebitDTO;

public interface AccountCommandService {

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}

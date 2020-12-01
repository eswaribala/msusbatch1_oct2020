package com.virtusa.banking.eventsourcingaxonspringboot.services.commands;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.virtusa.banking.eventsourcingaxonspringboot.commands.CreateAccountCommand;
import com.virtusa.banking.eventsourcingaxonspringboot.commands.CreditMoneyCommand;
import com.virtusa.banking.eventsourcingaxonspringboot.commands.DebitMoneyCommand;
import com.virtusa.banking.eventsourcingaxonspringboot.dto.commands.AccountCreateDTO;
import com.virtusa.banking.eventsourcingaxonspringboot.dto.commands.MoneyCreditDTO;
import com.virtusa.banking.eventsourcingaxonspringboot.dto.commands.MoneyDebitDTO;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
        return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency()));
    }

    @Override
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
        return commandGateway.send(new CreditMoneyCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency()));
    }

    @Override
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
        return commandGateway.send(new DebitMoneyCommand(accountNumber, moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency()));
    }
}

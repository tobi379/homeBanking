package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.service.TransactionService;
import com.mindhub.homebanking.ultilties.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @Autowired
    ClientService clientService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        if (accountService.getAccounts().isEmpty()) {
            return this.accountService.getAccounts().stream().map(AccountDTO::new).collect(toList());
        }
        return null;
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return this.accountService.getAccount(id).map(AccountDTO::new).orElse(null);
    }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication, @RequestParam AccountType type) {

        Client client = this.clientService.findByEmail(authentication.getName());

        if(client.getAccounts().stream().filter(Account::isStatus).count() == 3){
            return new ResponseEntity<>("Ya tienes 3 cuentas",HttpStatus.FORBIDDEN);
        }

        accountService.save(new Account("VIN"+ Utils.getRandomNumber(100,99999999),LocalDateTime.now(),0,client,type,true));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    

    @PostMapping("/accounts")
    public ResponseEntity<?> deleteAccount(@RequestParam String number){

        Account account = this.accountService.findByNumber(number);
        List<Transaction> transactions=this.transactionService.findByAccountNumber(number);
        account.setStatus(false);
        accountService.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

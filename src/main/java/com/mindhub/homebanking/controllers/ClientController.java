package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.ultilties.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mindhub.homebanking.ultilties.Utils.getRandomNumber;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    List<ClientDTO> getClients() {

        return clientService.getClients().stream().map(ClientDTO::new).collect(toList());
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        return new ClientDTO(this.clientService.findById(id));
    }


    @PostMapping("/clients")
    public ResponseEntity<?> register(@RequestParam String firstName, @RequestParam String lastName,
                                      @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Empty", HttpStatus.FORBIDDEN);
        }

        if (clientService.findByEmail(email) != null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }

        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientService.save(client);
        accountService.save(new Account("VIN"+ Utils.getRandomNumber(100, 999999999), LocalDateTime.now(),0,client, AccountType.Ahorro,true));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/clients/current")
    public ClientDTO getClientCurrent(Authentication authentication) {

        Client client = this.clientService.findByEmail(authentication.getName());
        Set<Account> cuentas = client.getAccounts().stream().filter(Account::isStatus).collect(Collectors.toSet());
        Set<Card> cards = client.getCards().stream().filter(Card::isStatus).collect(Collectors.toSet());

        client.setAccounts(cuentas);
        client.setCards(cards);
        return new ClientDTO(client);
    }
}

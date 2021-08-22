package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Account;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account findByNumber(String number);
    List<Account> findByStatus(boolean status);
    List<Account> getAccounts();
    Optional<Account> getAccount(long id);
    void save(Account account);
}

package src.main.java.org.glsid.accountservice.service;

import src.main.java.org.glsid.accountservice.model.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    Account getAccountById(UUID id);
    List<Account> getAllAccounts();
    Account createAccount(Account account);
    Account updateBalance(UUID id, double newBalance);
}
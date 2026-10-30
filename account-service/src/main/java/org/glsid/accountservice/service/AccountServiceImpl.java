package src.main.java.org.glsid.accountservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.main.java.org.glsid.accountservice.model.Account;
import src.main.java.org.glsid.accountservice.repository.AccountRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateBalance(UUID id, double newBalance) {
        Account account = getAccountById(id);
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }
}
package src.main.java.org.glsid.accountservice.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.main.java.org.glsid.accountservice.model.Account;
import src.main.java.org.glsid.accountservice.service.AccountService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {
    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable UUID id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PutMapping("/{id}/balance")
    public ResponseEntity<Account> updateBalance(@PathVariable UUID id, @RequestParam double newBalance) {
        return ResponseEntity.ok(accountService.updateBalance(id, newBalance));
    }
}
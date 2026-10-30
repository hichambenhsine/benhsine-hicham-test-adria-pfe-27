package src.main.java.org.glsid.accountservice.web.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import src.main.java.org.glsid.accountservice.model.Account;
import src.main.java.org.glsid.accountservice.model.AccountStatus;
import src.main.java.org.glsid.accountservice.service.AccountService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class AccountGraphQLController {
    private final AccountService accountService;

    public AccountGraphQLController(AccountService accountService) {
        this.accountService = accountService;
    }

    @QueryMapping
    public List<Account> allAccounts() {
        return accountService.getAllAccounts();
    }

    @QueryMapping
    public Account accountById(@Argument UUID id) {
        return accountService.getAccountById(id);
    }

    @MutationMapping
    public Account createAccount(@Argument Map<String, Object> account) {
        Account newAccount = Account.builder()
                .customerReference((String) account.get("customerReference"))
                .currency((String) account.get("currency"))
                .balance(((Number) account.get("balance")).doubleValue())
                .status(AccountStatus.ACTIVE)
                .build();
        return accountService.createAccount(newAccount);
    }
}
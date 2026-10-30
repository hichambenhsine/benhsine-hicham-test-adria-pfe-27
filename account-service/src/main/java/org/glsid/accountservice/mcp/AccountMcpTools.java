package src.main.java.org.glsid.accountservice.mcp;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import src.main.java.org.glsid.accountservice.model.Account;
import src.main.java.org.glsid.accountservice.service.AccountService;

import java.util.List;
import java.util.UUID;

@Component
public class AccountMcpTools {
    private final AccountService accountService;

    public AccountMcpTools(AccountService accountService) {
        this.accountService = accountService;
    }

    @Tool(description = "Récupérer la liste de tous les comptes bancaires")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @Tool(description = "Récupérer un compte bancaire à partir de son ID UUID")
    public Account getAccountById(@ToolParam(description = "Identifiant UUID du compte") String id) {
        return accountService.getAccountById(UUID.fromString(id));
    }
}
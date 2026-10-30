package org.glsid.transactionservice.mcp;

import org.glsid.transactionservice.model.Transaction;
import org.glsid.transactionservice.service.TransactionService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TransactionMcpTools {
    private final TransactionService transactionService;

    public TransactionMcpTools(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Tool(description = "Récupérer l'ensemble des transactions enregistrées")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @Tool(description = "Consulter les transactions associées à un compte bancaire précis")
    public List<Transaction> getTransactionsByAccount(@ToolParam(description = "UUID du compte bancaire") String accountId) {
        return transactionService.getTransactionsByAccountId(UUID.fromString(accountId));
    }
}
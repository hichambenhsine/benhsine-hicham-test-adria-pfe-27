package org.glsid.transactionservice.web.graphql;

import org.glsid.transactionservice.model.Transaction;
import org.glsid.transactionservice.model.TransferRequest;
import org.glsid.transactionservice.service.TransactionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class TransactionGraphQLController {
    private final TransactionService transactionService;

    public TransactionGraphQLController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @QueryMapping
    public List<Transaction> allTransactions() {
        return transactionService.getAllTransactions();
    }

    @QueryMapping
    public List<Transaction> transactionsByAccount(@Argument UUID accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }

    @MutationMapping
    public String executeTransfer(@Argument Map<String, Object> input) {
        TransferRequest request = new TransferRequest();
        request.setSourceAccountId(UUID.fromString((String) input.get("sourceAccountId")));
        request.setDestinationAccountId(UUID.fromString((String) input.get("destinationAccountId")));
        request.setAmount(BigDecimal.valueOf(((Number) input.get("amount")).doubleValue()));
        request.setCurrency((String) input.get("currency"));

        transactionService.processTransfer(request);
        return "Virement effectué avec succès via GraphQL.";
    }
}

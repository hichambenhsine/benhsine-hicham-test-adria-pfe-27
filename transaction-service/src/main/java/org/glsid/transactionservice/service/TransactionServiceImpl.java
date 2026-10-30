package org.glsid.transactionservice.service;

import org.glsid.transactionservice.feign.AccountDto;
import org.glsid.transactionservice.feign.AccountRestClient;
import org.glsid.transactionservice.model.Transaction;
import org.glsid.transactionservice.model.TransferRequest;
import org.glsid.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRestClient accountRestClient;

    @Value("${bank.transaction.max-amount:1000}")
    private BigDecimal maxAmount;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRestClient accountRestClient) {
        this.transactionRepository = transactionRepository;
        this.accountRestClient = accountRestClient;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getAmount().compareTo(maxAmount) > 0) {
            throw new IllegalArgumentException("Le montant dépasse le plafond autorisé de : " + maxAmount);
        }

        AccountDto account = accountRestClient.getAccountById(transaction.getAccountId());
        if ("BLOCKED".equalsIgnoreCase(account.getStatus())) {
            throw new IllegalStateException("Opération impossible sur un compte bloqué.");
        }

        return transactionRepository.save(transaction);
    }

    @Override
    public void processTransfer(TransferRequest request) {
        if (request.getAmount().compareTo(maxAmount) > 0) {
            throw new IllegalArgumentException("Le virement dépasse le plafond autorisé de : " + maxAmount);
        }

        AccountDto source = accountRestClient.getAccountById(request.getSourceAccountId());
        AccountDto dest = accountRestClient.getAccountById(request.getDestinationAccountId());

        if (source.getBalance() < request.getAmount().doubleValue()) {
            throw new IllegalStateException("Solde insuffisant pour exécuter le virement.");
        }

        accountRestClient.updateBalance(source.getId(), source.getBalance() - request.getAmount().doubleValue());
        accountRestClient.updateBalance(dest.getId(), dest.getBalance() + request.getAmount().doubleValue());

        Transaction debitTx = Transaction.builder()
                .accountId(source.getId())
                .amount(request.getAmount().negate())
                .currency(request.getCurrency())
                .merchantCategory("TRANSFER_OUT")
                .country("MA")
                .build();

        Transaction creditTx = Transaction.builder()
                .accountId(dest.getId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .merchantCategory("TRANSFER_IN")
                .country("MA")
                .build();

        transactionRepository.save(debitTx);
        transactionRepository.save(creditTx);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(UUID accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
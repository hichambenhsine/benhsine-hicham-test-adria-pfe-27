package org.glsid.transactionservice.service;

import org.glsid.transactionservice.model.Transaction;
import org.glsid.transactionservice.model.TransferRequest;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    void processTransfer(TransferRequest transferRequest);
    List<Transaction> getTransactionsByAccountId(UUID accountId);
    List<Transaction> getAllTransactions();
}
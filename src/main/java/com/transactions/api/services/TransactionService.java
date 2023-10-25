package com.transactions.api.services;

import com.transactions.api.domain.transaction.Transaction;
import com.transactions.api.dtos.TransactionDTO;

public interface TransactionService {
    Transaction createTransaction(TransactionDTO transactionDTO);

    boolean authorizeTransaction();
}

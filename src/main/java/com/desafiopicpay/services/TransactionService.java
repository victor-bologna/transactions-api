package com.desafiopicpay.services;

import com.desafiopicpay.domain.transaction.Transaction;
import com.desafiopicpay.dtos.TransactionDTO;

public interface TransactionService {
    Transaction createTransaction(TransactionDTO transactionDTO);

    boolean authorizeTransaction();
}

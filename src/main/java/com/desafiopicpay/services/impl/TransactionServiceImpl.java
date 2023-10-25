package com.desafiopicpay.services.impl;

import com.desafiopicpay.domain.responses.AuthorizationResponse;
import com.desafiopicpay.domain.transaction.Transaction;
import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.dtos.TransactionDTO;
import com.desafiopicpay.exception.TransactionException;
import com.desafiopicpay.repositories.TransactionRepository;
import com.desafiopicpay.services.NotificationService;
import com.desafiopicpay.services.TransactionService;
import com.desafiopicpay.services.UserService;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${transaction.validation.url}")
    private String validationUrl;

    public Transaction createTransaction(TransactionDTO transactionDTO) {
        User sender = this.userService.findUserById(transactionDTO.senderId());
        User receiver = this.userService.findUserById(transactionDTO.receiverId());

        this.userService.validateTransaction(sender, receiver, transactionDTO.value());

        boolean isAuthorized = this.authorizeTransaction();
        if(!isAuthorized) {
            throw new TransactionException("Transação não autorizada.");
        }

        Transaction transaction = new Transaction(null, transactionDTO.value(), sender, receiver, LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        this.transactionRepository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
        this.notificationService.sendNotification(sender, "Transação enviada com suceeso.");
        this.notificationService.sendNotification(receiver, "Transação recebida com suceeso.");

        return transaction;
    }

    public boolean authorizeTransaction() {
        ResponseEntity<AuthorizationResponse> authorizationResponse = restTemplate.getForEntity(validationUrl, AuthorizationResponse.class);

        if(authorizationResponse.getStatusCode().equals(HttpStatus.OK)){
            String message = Objects.requireNonNull(authorizationResponse.getBody()).message();
            return message.equals("Autorizado");
        }
        return false;
    }

}

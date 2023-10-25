package com.transactions.api.services.impl;

import com.transactions.api.domain.user.User;
import com.transactions.api.dtos.NotificationDTO;
import com.transactions.api.exception.TransactionException;
import com.transactions.api.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${notification.email.url}")
    private String emailUrl;

    public void sendNotification(User user, String message) {
        NotificationDTO notificationRequest = new NotificationDTO(user.getEmail(), message);
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(emailUrl, notificationRequest, String.class);

        if(!notificationResponse.getStatusCode().equals(HttpStatus.OK)) {
            throw new TransactionException("Serviço de notificação esta fora do ar.");
        }
    }
}

package com.desafiopicpay.services.impl;

import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.dtos.NotificationDTO;
import com.desafiopicpay.exception.TransactionException;
import com.desafiopicpay.services.NotificationService;
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

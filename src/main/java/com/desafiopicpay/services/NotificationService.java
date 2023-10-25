package com.desafiopicpay.services;

import com.desafiopicpay.domain.user.User;

public interface NotificationService {
    void sendNotification(User user, String message);
}

package com.transactions.api.services;

import com.transactions.api.domain.user.User;

public interface NotificationService {
    void sendNotification(User user, String message);
}

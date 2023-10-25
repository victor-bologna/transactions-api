package com.desafiopicpay.domain.transaction;

import com.desafiopicpay.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
public record Transaction (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        BigDecimal amount,
        @ManyToOne
        @JoinColumn(name = "sender_id")
        User sender,
        @ManyToOne
        @JoinColumn(name = "receiver_id")
        User receiver,
        LocalDateTime timestamp) {

}

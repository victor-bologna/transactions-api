package com.transactions.api.domain.transaction;

import com.transactions.api.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "transactions")
@Table(name = "transactions")
@Data
@AllArgsConstructor
public class Transaction {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        BigDecimal amount;
        @ManyToOne
        @JoinColumn(name = "sender_id")
        User sender;
        @ManyToOne
        @JoinColumn(name = "receiver_id")
        User receiver;
        LocalDateTime timestamp;
}

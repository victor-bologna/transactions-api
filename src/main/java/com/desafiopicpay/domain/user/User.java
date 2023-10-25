package com.desafiopicpay.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
public record User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        String firstName,
        String lastName,
        @Column(unique = true)
        String document,
        @Column(unique = true)
        String email,
        String password,
        BigDecimal balance,
        @Enumerated(EnumType.STRING)
        UserType userType) {

}

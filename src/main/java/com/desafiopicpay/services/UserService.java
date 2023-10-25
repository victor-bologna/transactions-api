package com.desafiopicpay.services;

import com.desafiopicpay.domain.user.User;
import com.desafiopicpay.dtos.UserDTO;
import com.desafiopicpay.exception.UserException;
import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    void validateTransaction(User sender, User receiver, BigDecimal amount);

    User findUserById(Long id) throws UserException;

    void saveUser(User user);

    User createUser(UserDTO userDTO);

    List<User> findAllUsers();
}

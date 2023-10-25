package com.transactions.api.services;

import com.transactions.api.domain.user.User;
import com.transactions.api.dtos.UserDTO;
import com.transactions.api.exception.UserException;
import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    void validateTransaction(User sender, User receiver, BigDecimal amount);

    User findUserById(Long id) throws UserException;

    void saveUser(User user);

    User createUser(UserDTO userDTO);

    List<User> findAllUsers();
}

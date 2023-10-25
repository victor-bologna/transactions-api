package com.transactions.api.services.impl;

import com.transactions.api.domain.user.User;
import com.transactions.api.domain.user.UserType;
import com.transactions.api.dtos.UserDTO;
import com.transactions.api.exception.UserException;
import com.transactions.api.repositories.UserRepository;
import com.transactions.api.services.UserService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void validateTransaction(User sender, User receiver, BigDecimal amount) {
        if(sender.getUserType().equals(UserType.MERCHANT)) {
            throw new UserException("Usuário do tipo lojista não esta autorizado a realizar transação.");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new UserException("Usuário não tem saldo suficiente.");
        }

        if(sender.getId().equals(receiver.getId())) {
            throw new UserException("Não é permitido fazer transação para si mesmo.");
        }
    }

    @Override
    public User findUserById(Long id) throws UserException {
        return this.userRepository.findUserById(id).orElseThrow(() -> new UserException("Usuário não encontrado."));
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User newUser = new User();
        BeanUtils.copyProperties(userDTO, newUser);
        this.saveUser(newUser);
        return newUser;
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }
}

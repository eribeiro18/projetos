package br.com.account.payment.api.application.service;

import org.springframework.stereotype.Service;

import br.com.account.payment.api.infraestructure.entity.User;
import br.com.account.payment.api.infraestructure.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}

package com.maia.vinicius.service;

import com.maia.vinicius.dto.UserRequest;
import com.maia.vinicius.exception.CreationException;
import com.maia.vinicius.mapper.UserMapper;
import com.maia.vinicius.model.User;
import com.maia.vinicius.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void registerUser(UserRequest userRequest){
        try {
            User user = UserMapper.toEntity(userRequest);
            user.setPassword(encoder.encode(userRequest.getPassword()));
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new CreationException("Dados inválidos para o user: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new CreationException("Erro ao registar o user", ex);
        }
    }
}

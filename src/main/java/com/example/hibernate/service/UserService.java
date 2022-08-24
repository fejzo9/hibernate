package com.example.hibernate.service;


import com.example.hibernate.exception.EntityNotFoundException;
import com.example.hibernate.model.AddUser;
import com.example.hibernate.model.UpdateUser;
import com.example.hibernate.model.User;
import com.example.hibernate.repository.UserRepository;

import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User getUser(UUID userId) {
        Optional<User> optUser = userRepository.findById(userId);

        if (optUser.isEmpty()) {
            throw new EntityNotFoundException("User", userId);
        } else
            return optUser.get();
    }

    public User newUser(final AddUser addUser) {
        return userRepository.save(new User(addUser.name(), addUser.address()));
    }

    public User updateUser(UUID id, UpdateUser updateUser) throws EntityNotFoundException {

        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            throw new EntityNotFoundException("User", id);
        }
        User user = optUser.get();
        user.setName(updateUser.name());
        user.setAddress(updateUser.address());
        return userRepository.save(user);

    }

    public User deleteById(UUID id) throws EntityNotFoundException {
        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isEmpty()) {
            throw new EntityNotFoundException("User", id);
        } else {
            userRepository.deleteById(id);
            return optUser.get();
        }
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}

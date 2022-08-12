package com.example.hibernate.service;

import com.example.hibernate.model.AddUser;
import com.example.hibernate.model.User;
import com.example.hibernate.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User getUser(UUID userId) throws Exception {
        Optional<User> optUser = userRepository.findById(userId);

        if (optUser.isEmpty()) {
            throw new Exception("error.../n The value is not present!");
        } else
            return optUser.get();
    }

    public User newUser(final AddUser addUser) {
        return userRepository.save(new User(addUser.name(), addUser.address()));
    }

    public User updateUser(@PathVariable("id") UUID id, @RequestBody User user) {

        User userPom = userRepository.findById(id).get();
        if (userPom.getId() == id) {
            userPom.setName(user.getName());
            userPom.setAdress(user.getAdress());
            return userPom;
        }
        return user;
    }

    public User deleteById(@PathVariable("id") UUID id) {
        userRepository.deleteById(id);
        return new User();
    }

    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

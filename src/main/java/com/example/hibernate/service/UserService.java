package com.example.hibernate.service;

import com.example.hibernate.exception.ApiRequestException;
import com.example.hibernate.model.AddUser;
import com.example.hibernate.model.User;
import com.example.hibernate.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
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

    public User getUser(UUID userId) throws EntityNotFoundException {
        Optional<User> optUser = userRepository.findById(userId);

        if (optUser.isEmpty()) {
            throw new EntityNotFoundException("error.../nSorry but we could not find a User with that ID/nPlease try again.");
        } else
            return optUser.get();
    }

    public User newUser(final AddUser addUser) {
        return userRepository.save(new User(addUser.name(), addUser.address()));
    }

    public User updateUser(UUID id, User user) {

        User userPom = userRepository.findById(id).get();
        if/*(userPom.isEmpty())
        {throw new Exception("error.../nThe user you are trying to find does not exist!/nTry with other id!");}
        else */(userPom.getId() == id) {
            userPom.setName(user.getName());
            userPom.setAdress(user.getAdress());
            return userPom;
        }
        return user;
    }

    public User deleteById(UUID id) throws Exception{
        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isEmpty()) {
            throw new Exception("error.../n The value is not present!/nDid not find the user!");
        } else{ userRepository.deleteById(id);
            return optUser.get();}
    }

    public void deleteAllUsers() {
      userRepository.deleteAll();
    }

}

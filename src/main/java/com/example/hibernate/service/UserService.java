package com.example.hibernate.service;


import com.example.hibernate.model.AddUser;
import com.example.hibernate.model.UpdateUser;
import com.example.hibernate.model.User;
import com.example.hibernate.repository.UserRepository;

import org.springframework.stereotype.Service;


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

    public User updateUser(UUID id, UpdateUser updateUser) throws EntityNotFoundException {

        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isEmpty())
        {throw new EntityNotFoundException("error.../nThe user you are trying to find does not exist!/nTry with other id!");}
        else /*if (userPom.getId() == id) */{
            User user = userRepository.findById(id).get();
            user.setName(updateUser.name());
            user.setAdress(updateUser.address());
            return userRepository.save(new User(updateUser.name(), updateUser.address()));
        }
    }

    public User deleteById(UUID id) throws EntityNotFoundException {
        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isEmpty()) {
            throw new EntityNotFoundException("error.../n Sorry but we could not find a user with that ID/nPlease try again");
        } else {
            userRepository.deleteById(id);
            return optUser.get();
        }
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}

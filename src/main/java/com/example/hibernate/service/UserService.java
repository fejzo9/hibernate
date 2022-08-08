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


    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User getUser(UUID userId) throws Exception {
        Optional<User> optUser = userRepository.findById(userId);

        if (optUser.isEmpty()) {
            throw new Exception("error...\n");
        } else
            return optUser.get();
    }

//    public ResponseEntity<User> addUser(final User u){
//        return userRepository.save(u);
//    }

    public User newUser(final AddUser addUser) {
        return userRepository.save(new User(addUser.name(), addUser.address()));
    }

    public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @RequestBody User user) {


        if (user.getId() == id) {
            User userPom = userRepository.getById(id);
            userPom.setName(user.getName());
            userPom.setAdress(user.getAdress());
            return new ResponseEntity<>(userPom, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


//        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        Optional<User> userData = userRepository.getById(id);
//        if (userData.isPresent()) {
//            User _user = userData.get();
//            _user.setName(user.getName());
//            _user.setAdress(user.getAdress());
//
//            return new ResponseEntity<>(newUser(_user), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

    public void deleteById(@PathVariable("id") UUID id) {
        userRepository.deleteById(id);
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

package com.example.hibernate.service;

import com.example.hibernate.entity.User;
import com.example.hibernate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> list() {
        return UserRepository.findAll();//ne znam da li je trebam overrideovati u UserRepository-u
    }
}

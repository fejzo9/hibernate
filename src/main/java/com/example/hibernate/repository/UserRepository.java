package com.example.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hibernate.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByName(String name);



}

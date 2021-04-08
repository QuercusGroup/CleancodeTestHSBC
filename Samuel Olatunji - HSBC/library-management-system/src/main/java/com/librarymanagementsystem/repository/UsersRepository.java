package com.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.librarymanagementsystem.model.User;

@Component
public interface UsersRepository extends JpaRepository<User, String> {
    
}

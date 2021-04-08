package com.librarymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagementsystem.model.User;
import com.librarymanagementsystem.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	UsersRepository usersRepository;
	
	public void addUser(User user){
		usersRepository.save(user);
    }

    public User getUser(String id){
        return usersRepository.findOne(id);
    }

    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }

    public Boolean isUserAvailable(String id) {
        return usersRepository.exists(id);
    }

    public void removeUser(String id) {
    	usersRepository.delete(id);
    }
	
}

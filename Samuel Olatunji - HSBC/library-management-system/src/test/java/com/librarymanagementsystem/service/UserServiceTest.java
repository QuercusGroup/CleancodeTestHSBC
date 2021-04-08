package com.librarymanagementsystem.service;

import com.librarymanagementsystem.model.User;
import com.librarymanagementsystem.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UsersRepository repository;

    @Test
    public void testAddUser() {
        User user = new User();
        Mockito.when(repository.save(user)).thenReturn(user);
        userService.addUser(user);
        Assert.notNull(user);
    }

    @Test
    public void testGetAllUsers() {
        List<User> list = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(list);
        list = userService.getAllUsers();
        Assert.notNull(list);
    }
}

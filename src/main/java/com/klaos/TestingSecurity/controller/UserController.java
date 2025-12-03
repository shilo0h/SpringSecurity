package com.klaos.TestingSecurity.controller;

import com.klaos.TestingSecurity.model.Users;
import com.klaos.TestingSecurity.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UsersService usersService;
    @PostMapping("/register")
    public Users register(@RequestBody Users users){
        return usersService.register(users);
    }
    @PostMapping("/login")
    public String login(Users users){
        return usersService.verify(users);
    }
}

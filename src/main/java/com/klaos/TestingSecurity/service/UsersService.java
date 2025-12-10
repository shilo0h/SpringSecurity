package com.klaos.TestingSecurity.service;

import com.klaos.TestingSecurity.model.Users;
import com.klaos.TestingSecurity.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;


    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public Users register(Users users){
        users.setEmail(encoder.encode(users.getEmail()));
        return repo.save(users);
    }
    public String verify(Users users){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getName(),users.getEmail()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(users.getName());

        return "fail";
    }
}

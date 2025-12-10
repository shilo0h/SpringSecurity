package com.klaos.TestingSecurity.service;

import com.klaos.TestingSecurity.model.UserPrincipal;
import com.klaos.TestingSecurity.model.Users;
import com.klaos.TestingSecurity.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
       Users user=repo.findByName(name);

       if (user ==null){
           System.out.println("User don't exist");
           throw  new UsernameNotFoundException("User not found");
       }
       return new UserPrincipal(user);
    }

}

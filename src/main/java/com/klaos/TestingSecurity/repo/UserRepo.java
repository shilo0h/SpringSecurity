package com.klaos.TestingSecurity.repo;

import com.klaos.TestingSecurity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByName(String name);
}

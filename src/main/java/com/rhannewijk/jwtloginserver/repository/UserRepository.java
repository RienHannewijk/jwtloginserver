package com.rhannewijk.jwtloginserver.repository;

import com.rhannewijk.jwtloginserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String name);
}

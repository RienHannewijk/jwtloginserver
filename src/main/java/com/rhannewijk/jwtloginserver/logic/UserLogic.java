package com.rhannewijk.jwtloginserver.logic;

import com.rhannewijk.jwtloginserver.exception.UserNotFoundException;
import com.rhannewijk.jwtloginserver.model.User;
import com.rhannewijk.jwtloginserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogic {

    @Autowired
    private UserRepository repository;

    public User getUser(int id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getUserByName(String name){ return repository.findByUsername(name); }

    public User updateUser(User user) {
        return repository.save(user);
    }

    public void deleteUser(int id){
        repository.deleteById(id);
    }
}

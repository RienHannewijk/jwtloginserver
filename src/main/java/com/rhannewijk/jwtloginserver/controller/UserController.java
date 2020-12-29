package com.rhannewijk.jwtloginserver.controller;

import com.rhannewijk.jwtloginserver.authentication.utility.AuthUtil;
import com.rhannewijk.jwtloginserver.controller.assembler.UserModelAssembler;
import com.rhannewijk.jwtloginserver.logic.UserLogic;
import com.rhannewijk.jwtloginserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserLogic logic;

    @Autowired
    private UserModelAssembler assembler;

    @GetMapping("/user")
    @ResponseBody
    public EntityModel<User> getLoggedInUser() {
        User user = logic.getUserByName(AuthUtil.getCurrentUsername());
        return assembler.toModel(user);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = logic.getUser(id);
        return assembler.toModel(user);
    }

    @GetMapping("/user/name/{name}")
    @ResponseBody
    public EntityModel<User> getUserByName(@PathVariable String name) {
        User user = logic.getUserByName(name);
        return assembler.toModel(user);
    }

    @PutMapping("/user")
    @ResponseBody
    public User putUser(@RequestBody User user) {
        return logic.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable int id) {
        logic.deleteUser(id);
    }

}

package com.rhannewijk.jwtloginserver.controller;

import com.rhannewijk.jwtloginserver.authentication.utility.AuthUtil;
import com.rhannewijk.jwtloginserver.controller.assembler.UserModelAssembler;
import com.rhannewijk.jwtloginserver.dto.UserDto;
import com.rhannewijk.jwtloginserver.logic.UserLogic;
import com.rhannewijk.jwtloginserver.model.User;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/user")
    @ResponseBody
    public EntityModel<UserDto> getLoggedInUser() {
        UserDto user = convertToDto(logic.getUserByName(AuthUtil.getCurrentUsername()));
        System.out.println(user.getId());
        return assembler.toModel(user);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public EntityModel<UserDto> getUser(@PathVariable int id) {
        UserDto user = convertToDto(logic.getUser(id));
        return assembler.toModel(user);
    }

    @GetMapping("/user/name/{name}")
    @ResponseBody
    public EntityModel<UserDto> getUserByName(@PathVariable String name) {
        UserDto user = convertToDto(logic.getUserByName(name));
        return assembler.toModel(user);
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable int id) {
        logic.deleteUser(id);
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}

package com.rhannewijk.jwtloginserver.controller.assembler;


import com.rhannewijk.jwtloginserver.controller.UserController;
import com.rhannewijk.jwtloginserver.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {

        return new EntityModel<>(user,
                WebMvcLinkBuilder.linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel());
    }
}

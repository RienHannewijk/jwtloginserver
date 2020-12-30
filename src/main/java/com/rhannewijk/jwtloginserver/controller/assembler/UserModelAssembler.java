package com.rhannewijk.jwtloginserver.controller.assembler;

import com.rhannewijk.jwtloginserver.controller.UserController;
import com.rhannewijk.jwtloginserver.dto.UserDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserDto, EntityModel<UserDto>> {

    @Override
    public EntityModel<UserDto> toModel(UserDto user) {

        EntityModel<UserDto> entityModel = EntityModel.of(user);
        Link link= WebMvcLinkBuilder.linkTo(
                methodOn(UserController.class).getUser(user.getId())).withSelfRel();
        entityModel.add(link);

        return entityModel;
    }
}

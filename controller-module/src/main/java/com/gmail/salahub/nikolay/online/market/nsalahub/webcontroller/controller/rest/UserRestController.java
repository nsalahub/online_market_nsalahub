package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.controller.rest;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.UserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.user.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public ResponseEntity saveArticle(
            @RequestBody UserDTO userDTO
    ) {
        userService.create(userDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

package com.sistemapacto.server.controllers;

import com.sistemapacto.server.dto.user.UserCreateDTO;
import com.sistemapacto.server.dto.user.UserDTO;
import com.sistemapacto.server.exceptions.BusinessException;
import com.sistemapacto.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createNewUser(@Valid @RequestBody UserCreateDTO userCreateDTO)
            throws BusinessException, IOException {
        return new ResponseEntity<>(userService.createNewUser(userCreateDTO), HttpStatus.CREATED);
    }
}

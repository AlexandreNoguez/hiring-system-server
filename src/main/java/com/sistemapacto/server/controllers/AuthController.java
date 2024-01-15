package com.sistemapacto.server.controllers;

import com.sistemapacto.server.dto.login.LoginDTO;
import com.sistemapacto.server.exceptions.BusinessException;
import com.sistemapacto.server.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signIn")
    public String authenticate(@RequestBody @Valid LoginDTO loginDTO) throws BusinessException {
        log.info("Verificando autenticação . . .");
        return (authService.authenticate(loginDTO));
    }
}

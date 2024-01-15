package com.sistemapacto.server.services;

import com.sistemapacto.server.dto.login.LoginDTO;
import com.sistemapacto.server.entities.LoginUserLogs;
import com.sistemapacto.server.entities.UserEntity;
import com.sistemapacto.server.exceptions.BusinessException;
import com.sistemapacto.server.repositories.LoginUserLogsRepository;
import com.sistemapacto.server.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final LoginUserLogsRepository loginUserLogsRepository;
    private final ZoneId ZONE_BRAZIL = ZoneId.of("America/Sao_Paulo");

    public String authenticate(LoginDTO loginDTO) throws BusinessException {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUserLogin(),
                            loginDTO.getUserPassword()
                    );
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            Object principal = authentication.getPrincipal();
            UserEntity user = (UserEntity) principal;

            loginUserLogsRepository.save(new LoginUserLogs(user, ZonedDateTime.now(ZONE_BRAZIL).toLocalDateTime()));
            return tokenService.getToken(user);
        } catch (AuthenticationException e) {
            throw new BusinessException("Usuário não encontrado");
        }

    }
}

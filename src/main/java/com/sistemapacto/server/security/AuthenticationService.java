package com.sistemapacto.server.security;

import com.sistemapacto.server.entities.UserEntity;
import com.sistemapacto.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userService.findByUserLogin(username);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("Inválido"));
    }
}

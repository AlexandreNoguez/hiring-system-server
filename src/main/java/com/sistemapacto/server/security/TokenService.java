package com.sistemapacto.server.security;

import com.sistemapacto.server.dto.login.LoginDTO;
import com.sistemapacto.server.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {
    private static final String KEY_ROLES = "ROLES";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.last.token}")
    private String expireToken;

    public String getToken(UserEntity userEntity) {
        LocalDate agoraLocalDate = LocalDate.now();
        Date dataAgora = Date.from(agoraLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate duraçãoLocalDate = LocalDate.now().plusDays(Long.parseLong(expireToken));
        Date duracaoToken = Date.from(duraçãoLocalDate.atStartOfDay((ZoneId.systemDefault())).toInstant());

        List<String> userRoles = userEntity.getUserRole().stream()
                .map(usuarioCargoPK -> {
                    return usuarioCargoPK.getRoleEntity().getAuthority();
                })
                .toList();

        String jwtToken = Jwts.builder()
                .setIssuer("sistemapacto-api")
                .claim(Claims.ID, userEntity.getUserId().toString())
                .claim(KEY_ROLES, userRoles)
                .setIssuedAt(dataAgora)
                .setExpiration(duracaoToken)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return jwtToken;
    }

    public UsernamePasswordAuthenticationToken isValid(String token) {
        if(token == null) {
            return null;
        }
//        token = token.replace("Bearer ", "");

        Claims chaves = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
        //jti = ID
        String userId = chaves.get(Claims.ID, String.class);
        List<String> cargos = chaves.get(KEY_ROLES, List.class);

        List<SimpleGrantedAuthority> listaDeCargos = cargos.stream()
                .map(roles -> {
                    return new SimpleGrantedAuthority(roles);
                })
                .toList();

        UsernamePasswordAuthenticationToken tokenObject = new UsernamePasswordAuthenticationToken(userId,
                null,
                listaDeCargos);

        return tokenObject;
    }

    public String autenticarAcesso(LoginDTO loginDTO, AuthenticationManager authenticationManager) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUserLogin(),
                        loginDTO.getUserPassword()
                );
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Object principal = authentication.getPrincipal();
        UserEntity usuarioEntity = (UserEntity) principal;
        return getToken(usuarioEntity);

    }
}

package com.sistemapacto.server.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final TokenService tokenService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers()
                .frameOptions().disable()
                .and().cors()
                .and().csrf().disable()
                .authorizeHttpRequests((authz) ->
                        authz
                                .antMatchers("/", "/api/auth/signIn").permitAll()
                                .antMatchers("/api/user/register").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/job/getAllByParams").permitAll()
                                .antMatchers(HttpMethod.PATCH, "/api/job/application").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/skill/**").permitAll()

//                                 Casos de uso que pensei em implementar mas para testes e experimentação deixei comentado

//                                .antMatchers(HttpMethod.POST, "/api/job/register").hasRole("ROLE_RECRUITER")
//                                .antMatchers(HttpMethod.POST, "/api/**").hasRole("ROLE_ADMIN")
//                                .antMatchers(HttpMethod.GET, "/api/**").hasRole("ROLE_ADMIN")
//                                .antMatchers(HttpMethod.PUT, "/api/**").hasRole("ROLE_ADMIN")
//                                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ROLE_ADMIN")
                                .anyRequest().authenticated()
                );
        http.addFilterBefore(new TokenAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**"
        );
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
//                Para fins de teste deixei o cors aberto para todos hosts
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .exposedHeaders("Authorization");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

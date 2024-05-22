package org.feignwebfluxdocker.security.api.config;

import org.feignwebfluxdocker.security.api.config.jwt.JwtFilter;
import org.feignwebfluxdocker.security.core.repositories.SecurityContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class MainSecurity {

    private final SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http, JwtFilter jwtFilter) {
        return http
                .cors().and()
                .authorizeExchange()
                .pathMatchers("/api/auth/**").permitAll()
                .anyExchange().permitAll()
                .and()
                .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
                .securityContextRepository(securityContextRepository)
                .formLogin().disable()
                .logout().disable()
                .httpBasic().disable()
                .csrf().disable()
                .build();
    }
}

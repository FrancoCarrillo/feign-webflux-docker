package org.feignwebfluxdocker.security.api.router;

import org.feignwebfluxdocker.security.api.handler.UserHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class UserRouter {

    private static final String PATH_USER = "/api/auth/user";

    @Bean
    RouterFunction<ServerResponse> userRtr(UserHandler handler) {
        return RouterFunctions.route()
                .POST(PATH_USER + "/login", handler::login)
                .POST(PATH_USER, handler::create)
                .build();
    }
}

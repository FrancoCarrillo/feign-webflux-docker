package org.feignwebfluxdocker.security.infrastructure.interfaces;

import org.feignwebfluxdocker.security.api.model.request.CreateUserRequest;
import org.feignwebfluxdocker.security.api.model.request.LoginRequest;
import org.feignwebfluxdocker.security.api.model.response.LoginResponse;
import org.feignwebfluxdocker.shared.models.response.MessageResponse;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<LoginResponse> login(LoginRequest request);
    Mono<MessageResponse> createUser(CreateUserRequest request);
}

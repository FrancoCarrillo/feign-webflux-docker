package org.feignwebfluxdocker.security.infrastructure.services;


import org.feignwebfluxdocker.security.api.config.jwt.JwtProvider;
import org.feignwebfluxdocker.security.api.model.request.CreateUserRequest;
import org.feignwebfluxdocker.security.api.model.request.LoginRequest;
import org.feignwebfluxdocker.security.api.model.response.LoginResponse;
import org.feignwebfluxdocker.security.core.entities.UserAccount;
import org.feignwebfluxdocker.security.core.enums.Role;
import org.feignwebfluxdocker.security.core.repositories.UserAccountRepository;
import org.feignwebfluxdocker.security.infrastructure.interfaces.IUserService;
import org.feignwebfluxdocker.shared.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feignwebfluxdocker.shared.models.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserAccountRepository userAccountRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Mono<LoginResponse> login(LoginRequest request) {
        return userAccountRepository.findByUsernameOrEmail(request.getUsername(), request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(jwtProvider::generateToken)
                .map(LoginResponse::new)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Invalid credentials")));
    }

    @Override
    public Mono<MessageResponse> createUser(CreateUserRequest request) {
        UserAccount userAccount = UserAccount.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .roles(Role.ROLE_USER.name())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Mono<Boolean> userExists = userAccountRepository.findByUsernameOrEmail(userAccount.getUsername(), userAccount.getEmail()).hasElement();

        return userExists.flatMap(exists -> {
            if (exists) {
                return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "User already exists"));
            } else {
                return userAccountRepository.save(userAccount)
                        .map(user -> new MessageResponse("User with id: " + user.getId().toString() + " created successfully"));
            }
        });

    }

}

package com.gleestorm.cargo.authentication.service;


import com.gleestorm.cargo.authentication.dto.AuthenticationRequest;
import com.gleestorm.cargo.authentication.dto.AuthenticationResponse;
import com.gleestorm.cargo.authentication.dto.RegisterRequest;
import com.gleestorm.cargo.config.JwtService;
import com.gleestorm.cargo.authentication.model.User;
import com.gleestorm.cargo.authentication.repository.UserRepository;
import com.gleestorm.cargo.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        //If the authentication succeed, we generate and return the token
        var user = repository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            throw new UserNotFoundException("Cannot found the user: " + request.getEmail());
        }

        var jwtToken = jwtService.generateToken(user.get());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

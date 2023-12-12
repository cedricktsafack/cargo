package com.gleestorm.cargo.authentication.controller;

import com.gleestorm.cargo.authentication.dto.AuthenticationRequest;
import com.gleestorm.cargo.authentication.dto.AuthenticationResponse;
import com.gleestorm.cargo.authentication.dto.RegisterRequest;
import com.gleestorm.cargo.authentication.service.AuthenticationService;
import com.gleestorm.cargo.exceptions.UserNotFoundException;
import com.gleestorm.cargo.core.utils.apiResponse.ApiResponseMetadata;
import com.gleestorm.cargo.core.utils.apiResponse.MyApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "API to create account and login")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " user registred Register user")
    })
    public ResponseEntity<MyApiResponse<Object>> register(
            @RequestBody RegisterRequest request
    ) {
        Instant processStart = Instant.now();

        AuthenticationResponse response = service.register(request);

        ApiResponseMetadata metadata = ApiResponseMetadata.builder()
                .error(null)
                .paging(null)
                .processingStart(processStart)
                .processingEnd(Instant.now())
                .build();

        MyApiResponse<Object> apiResponse =MyApiResponse.builder()
                .data(response)
                .metadata(metadata)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(summary = "Authenticate user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " user authenticated successfully"),
            @ApiResponse(responseCode = "403", description = " UnAutorized. Authentication failed")
    })
    @PostMapping("/authenticate")
    public ResponseEntity<MyApiResponse<Object>> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws UserNotFoundException {


        Instant processStart = Instant.now();


       AuthenticationResponse authRes=  service.authenticate(request);

        ApiResponseMetadata metadata = ApiResponseMetadata.builder()
                .error(null)
                .paging(null)
                .processingStart(processStart)
                .processingEnd(Instant.now())
                .build();
        MyApiResponse<Object> apiResponse =MyApiResponse.builder()
                .data(authRes)
                .metadata(metadata)
                .build();

        return ResponseEntity.ok(apiResponse);

    }
}

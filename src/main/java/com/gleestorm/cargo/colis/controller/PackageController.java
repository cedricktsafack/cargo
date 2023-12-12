package com.gleestorm.cargo.colis.controller;


import com.gleestorm.cargo.authentication.dto.AuthenticationResponse;
import com.gleestorm.cargo.authentication.dto.RegisterRequest;
import com.gleestorm.cargo.core.utils.apiResponse.ApiResponseMetadata;
import com.gleestorm.cargo.core.utils.apiResponse.MyApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/package")
@RequiredArgsConstructor
@Tag(name = "Package", description = "API for the package and package group")
public class PackageController {


}

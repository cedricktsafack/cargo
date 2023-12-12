package com.gleestorm.cargo.packages.controller;


import com.gleestorm.cargo.authentication.dto.AuthenticationResponse;
import com.gleestorm.cargo.authentication.dto.RegisterRequest;
import com.gleestorm.cargo.core.utils.apiResponse.ApiResponseMetadata;
import com.gleestorm.cargo.core.utils.apiResponse.MyApiResponse;
import com.gleestorm.cargo.packages.dto.CreatePackageRequest;
import com.gleestorm.cargo.packages.model.Package;
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

    /*
    @PostMapping
    @Operation(summary = "Save a package")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = " Package created")
    })
    public ResponseEntity<MyApiResponse<Package>> save(
            @RequestBody CreatePackageRequest request
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

     */
}

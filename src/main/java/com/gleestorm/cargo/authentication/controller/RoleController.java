package com.gleestorm.cargo.authentication.controller;

import com.gleestorm.cargo.authentication.dto.user.UserResponse;
import com.gleestorm.cargo.authentication.dto.role.AddRoleRequest;
import com.gleestorm.cargo.authentication.dto.role.AddRoleToUserRequest;
import com.gleestorm.cargo.authentication.dto.role.RoleResponse;
import com.gleestorm.cargo.authentication.service.RoleService;
import com.gleestorm.cargo.exceptions.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_USER')")
@Tag(name = "Role", description = "API to create roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    @Operation(summary = "Create a rôle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = " Role created")
    })
    public ResponseEntity<RoleResponse> createRole(
            @RequestBody AddRoleRequest request
    ) {
        return ResponseEntity.ok(roleService.saveRole(request));
    }

    @PostMapping("/add")
    @Operation(summary = "Add new role to the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = " Role Added to the user")
    })
    public ResponseEntity<UserResponse> addRoleToUser(
            @RequestBody AddRoleToUserRequest request
    ) throws UserNotFoundException {
        return ResponseEntity.ok(roleService.addRoleToUser(request));
    }

}

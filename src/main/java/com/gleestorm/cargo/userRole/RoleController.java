package com.gleestorm.cargo.userRole;

import com.gleestorm.cargo.auth.AuthenticationResponse;
import com.gleestorm.cargo.auth.RegisterRequest;
import com.gleestorm.cargo.user.dto.UserResponse;
import com.gleestorm.cargo.userRole.dato.AddRoleRequest;
import com.gleestorm.cargo.userRole.dato.AddRoleToUserRequest;
import com.gleestorm.cargo.userRole.dato.RoleResponse;
import com.gleestorm.cargo.userRole.service.RoleService;
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

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
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

    @PostMapping("/Add")
    @Operation(summary = "Add new role to the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = " Role Added to the user")
    })
    public ResponseEntity<UserResponse> addRoleToUser(
            @RequestBody AddRoleToUserRequest request
    ) {
        return ResponseEntity.ok(roleService.addRoleToUser(request));
    }

}

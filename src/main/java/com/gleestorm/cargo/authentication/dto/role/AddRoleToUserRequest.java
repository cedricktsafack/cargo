package com.gleestorm.cargo.authentication.dto.role;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddRoleToUserRequest {

    @NotNull(message = "The email address is required")
    private String email;

    @NotNull(message = "The role ID is required")
    private String roleId;
}

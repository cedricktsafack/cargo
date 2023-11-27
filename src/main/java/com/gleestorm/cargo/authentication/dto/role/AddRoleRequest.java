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
public class AddRoleRequest {

    @NotNull(message = "The rôle name is required")
    private String name;
}

package com.gleestorm.cargo.userRole.dato;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddRoleToUserRequest {
    private String email;
    private Integer roleId;
}

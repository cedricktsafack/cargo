package com.gleestorm.cargo.userRole.dato;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddRoleRequest {
    private Integer id;

    private String name;
}

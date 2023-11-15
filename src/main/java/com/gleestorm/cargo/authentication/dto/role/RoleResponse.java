package com.gleestorm.cargo.authentication.dto.role;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleResponse {
    private Integer id;

    private String name;
}

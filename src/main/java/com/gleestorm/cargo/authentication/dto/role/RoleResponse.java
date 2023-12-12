package com.gleestorm.cargo.authentication.dto.role;


import com.gleestorm.cargo.core.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleResponse extends BaseEntity {
    private String id;

    private String name;
}

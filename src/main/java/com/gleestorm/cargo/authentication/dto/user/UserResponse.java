package com.gleestorm.cargo.authentication.dto.user;

import com.gleestorm.cargo.authentication.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private Set<Role> roles = new HashSet<>();
}
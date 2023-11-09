package com.gleestorm.cargo.user.dto;

import com.gleestorm.cargo.userRole.Role;
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
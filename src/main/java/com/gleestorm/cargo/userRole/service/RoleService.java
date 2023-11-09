package com.gleestorm.cargo.userRole.service;


import com.gleestorm.cargo.user.UserRepository;
import com.gleestorm.cargo.user.dto.UserResponse;
import com.gleestorm.cargo.userRole.Role;
import com.gleestorm.cargo.userRole.RoleRepository;
import com.gleestorm.cargo.userRole.dato.AddRoleRequest;
import com.gleestorm.cargo.userRole.dato.AddRoleToUserRequest;
import com.gleestorm.cargo.userRole.dato.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleResponse saveRole(AddRoleRequest role) {

        var request = Role.builder().name(role.getName()).build();

        var result = roleRepository.save(request);

        return RoleResponse.builder().name(result.getName()).id(result.getId()).build();
    }

    public Set<RoleResponse> getRoles() {

        var result = roleRepository.findAll();

        var responses = result.stream().map((role1 -> RoleResponse.builder().name(role1.getName()).id(role1.getId()).build()));

        return responses.collect(Collectors.toSet());
    }

    public UserResponse addRoleToUser(AddRoleToUserRequest request){

        var user =userRepository.findByEmail(request.getEmail()).orElseThrow();
        var role = roleRepository.findById(request.getRoleId()).orElseThrow();
        user.getRoles().add(role);

        var updatedUser = userRepository.save(user);

        return UserResponse
                .builder()
                .email(updatedUser.getEmail())
                .firstname(updatedUser.getFirstname())
                .lastname(updatedUser.getLastname())
                .roles(updatedUser.getRoles())
                .id(updatedUser.getId())
                .build();
    }

    //TODO implement removing role to the user


}


package com.gleestorm.cargo.authentication.service;


import com.gleestorm.cargo.authentication.repository.UserRepository;
import com.gleestorm.cargo.authentication.dto.user.UserResponse;
import com.gleestorm.cargo.authentication.model.Role;
import com.gleestorm.cargo.authentication.repository.RoleRepository;
import com.gleestorm.cargo.authentication.dto.role.AddRoleRequest;
import com.gleestorm.cargo.authentication.dto.role.AddRoleToUserRequest;
import com.gleestorm.cargo.authentication.dto.role.RoleResponse;
import com.gleestorm.cargo.exceptions.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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

    public UserResponse addRoleToUser(AddRoleToUserRequest request) throws UserNotFoundException {

        var userFind =userRepository.findByEmail(request.getEmail());

        if (userFind.isEmpty()) {
            throw new UserNotFoundException("Cannot found the user: " + request.getEmail());
        }
        var roleFind = roleRepository.findById(request.getRoleId());
        if (roleFind.isEmpty()) {
            throw new EntityNotFoundException("Cannot found the role: " + request.getRoleId());
        }

        userFind.get().getRoles().add(roleFind.get());

        var updatedUser = userRepository.save(userFind.get());

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


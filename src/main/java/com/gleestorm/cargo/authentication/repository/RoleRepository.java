package com.gleestorm.cargo.authentication.repository;

import com.gleestorm.cargo.authentication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}

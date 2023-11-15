package com.gleestorm.cargo.runners;


import com.gleestorm.cargo.authentication.dto.role.AddRoleRequest;
import com.gleestorm.cargo.authentication.dto.role.RoleResponse;
import com.gleestorm.cargo.authentication.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
class RoleRunners implements CommandLineRunner {

    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {

        Logger.getLogger(RoleRunners.class.getName()).log(Level.INFO, "*** Create defaults rôle");

        List<AddRoleRequest> roles = new ArrayList<>();

        roles.add(new AddRoleRequest("USER"));
        roles.add(new AddRoleRequest("MANAGER"));
        roles.add(new AddRoleRequest("ADMIN"));
        roles.add(new AddRoleRequest("SUPER_ADMIN"));

        for (AddRoleRequest role : roles) {
            roleService.saveRole(role);
        }

        var allRoles = roleService.getRoles();

        Logger.getLogger(RoleRunners.class.getName()).log(Level.INFO, "**** All roles inserted\n");

        for (RoleResponse role:allRoles){

            Logger.getLogger(RoleRunners.class.getName()).log(Level.INFO, role.toString()+" \n");
        }

    }
}

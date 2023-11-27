package com.gleestorm.cargo.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/verify-controller")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class VerifySecurityController {


    @GetMapping
    public ResponseEntity<String> verify(){
        return ResponseEntity.ok("This end point is secured, so you have access!");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/verifyAdmin")
    public ResponseEntity<String> verifyAdmin(){
        return ResponseEntity.ok("admin role");
    }
}

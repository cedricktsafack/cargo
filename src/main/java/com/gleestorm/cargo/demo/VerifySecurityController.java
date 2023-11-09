package com.gleestorm.cargo.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/verify-controller")
public class VerifySecurityController {

    @GetMapping
    public ResponseEntity<String> verify(){
        return ResponseEntity.ok("This end point is secured, so you have access!");
    }
}

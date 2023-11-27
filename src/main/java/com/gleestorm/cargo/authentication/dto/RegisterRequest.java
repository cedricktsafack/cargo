package com.gleestorm.cargo.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(message = "First name is required")
    private String firstname;

    @NotNull(message = "lastname is required")
    private String lastname;

    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull(message = "The password is required")
    private String password;

    @NotNull(message = "The phone number is require")
    private String phoneNumber;

}

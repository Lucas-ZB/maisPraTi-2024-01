package com.example.api_user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDTO {
    private int id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @Email(message = "Email should be valid")
    private String email;

    private String role;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
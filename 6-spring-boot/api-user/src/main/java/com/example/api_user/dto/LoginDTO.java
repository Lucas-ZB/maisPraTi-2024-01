package com.example.api_user.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; // Use javax.validation se estiver em uma versão anterior ao Spring Boot 3

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Username is mandatory") // Valida que o username não pode ser vazio
    private String username; // Nome de usuário para autenticação

    @NotBlank(message = "Password is mandatory") // Valida que a senha não pode ser vazia
    private String password; // Senha do usuário para autenticação
}
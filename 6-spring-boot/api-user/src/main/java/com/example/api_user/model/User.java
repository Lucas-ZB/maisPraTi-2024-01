package com.example.api_user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // Importação para validação de e-mail
import jakarta.persistence.Table; // Importação para validação de campos não vazios
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // ID único do usuário.

    @NotBlank(message = "Username is required") // Validação para garantir que o username não seja vazio
    @Column(nullable = false, unique = true) // Adicionando unique = true para garantir que o username seja único
    private String username; // Nome de usuário.

    @NotBlank(message = "Password is required") // Validação para garantir que a password não seja vazia
    @Column(nullable = false)
    private String password; // Senha do usuário.

    @NotBlank(message = "Email is required") // Validação para garantir que o email não seja vazio
    @Email(message = "Email should be valid") // Validação para garantir que o email tenha um formato válido
    @Column(nullable = false, unique = true) // Adicionando unique = true para garantir que o email seja único
    private String email; // Endereço de e-mail do usuário.

    @NotBlank(message = "Role is required") // Validação para garantir que o role não seja vazio
    @Column(nullable = false)
    private String role; // Papel ou função do usuário na aplicação.

    // Removido método orElseThrow não utilizado.
}
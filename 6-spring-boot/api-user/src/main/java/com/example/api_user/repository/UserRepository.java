package com.example.api_user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.api_user.model.User;

// Interface UserRepository que estende CrudRepository para operações CRUD na entidade User.
public interface UserRepository extends CrudRepository<User, Integer> {
    
    // Método para encontrar um usuário pelo nome de usuário.
    Optional<User> findByUsername(String username);
    
    // Método para encontrar um usuário pelo email (opcional).
    Optional<User> findByEmail(String email);
}
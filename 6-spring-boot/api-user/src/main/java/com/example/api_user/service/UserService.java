package com.example.api_user.service;

import java.util.List; // Data Transfer Object (DTO) que encapsula os dados do usuário.
import java.util.Optional; // Classe modelo que representa a entidade User no banco de dados.
import java.util.stream.Collectors; // Repositório que permite a comunicação com o banco de dados para a entidade User.
import java.util.stream.StreamSupport; // Injeta automaticamente dependências (beans) gerenciadas pelo Spring.

import org.springframework.beans.factory.annotation.Autowired; // Classe usada para criptografar senhas com o algoritmo BCrypt.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Marca a classe como um serviço Spring;
import org.springframework.stereotype.Service; // Interface da coleção List usada para armazenar múltiplos objetos.

import com.example.api_user.dto.UserDTO; // Classe que pode ou não conter um valor, usada para evitar null pointers.
import com.example.api_user.model.User; // Coletor do pacote Stream usado para transformar fluxos de dados em coleções.
import com.example.api_user.repository.UserRepository;

// Anotação @Service: Marca esta classe como um serviço gerenciado pelo Spring.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Injetando o BCryptPasswordEncoder

    public List<UserDTO> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToDTO).orElse(null);
    }

    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Nome de usuário já existe"); // Tratamento de erro se o usuário já existir
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Usando o encoder injetado

        userRepository.save(user);
        return convertToDTO(user);
    }

    public UserDTO updateUser(int id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setRole(userDTO.getRole());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Atualizando a senha

            userRepository.save(user);
            return convertToDTO(user);
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) { // Verifica se o usuário existe
            userRepository.deleteById(id);
            return true; // Retorna true se a exclusão foi bem-sucedida
        }
        return false; // Retorna false se o usuário não foi encontrado
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
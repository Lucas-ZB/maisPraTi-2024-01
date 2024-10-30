package com.example.api_user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; // Importando UserBuilder para construção do UserDetails

import com.example.api_user.model.User;
import com.example.api_user.repository.UserRepository;

// Anotação @Service: Marca esta classe como um serviço gerenciado pelo Spring.
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo nome de usuário usando o UserRepository.
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // Se o usuário não for encontrado, lança uma exceção UsernameNotFoundException.
        User user = optionalUser.orElseThrow(() -> 
            new UsernameNotFoundException("Usuário não encontrado: " + username)
        );

        // Cria um objeto UserDetails incluindo as autoridades (roles) do usuário.
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .disabled(false); // Você pode ajustar isso com base na lógica de desativação de usuários.

        // Aqui, você pode adicionar as autoridades se houver alguma lógica para isso.
        // Exemplo: userBuilder.authorities(user.getRoles()); se você tiver um método getRoles() na classe User.

        return userBuilder.build(); // Retorna o objeto UserDetails
    }
}
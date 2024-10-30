package com.example.api_user.dto;

public class AuthResponse {

    private String token; // Atributo para armazenar o token

    // Construtor
    public AuthResponse(String token) {
        this.token = token; // Inicializa o atributo
    }

    // Método getter para acessar o token
    public String getToken() {
        return token;
    }

    // (Opcional) Método setter, caso você precise modificar o token depois
    public void setToken(String token) {
        this.token = token;
    }
}
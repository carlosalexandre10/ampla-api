package br.com.ampla.marca.config.security.jwt;

import lombok.Data;

@Data
public class JwtLoginInput {
    private String email;
    private String senha;
}
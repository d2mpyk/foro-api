package com.d2mp.foro.infra.security;

import jakarta.validation.constraints.NotBlank;

public record DTOAutenticacionUsuario(
        @NotBlank(message = "El email es obligatorio")
        String email,
        @NotBlank(message = "El password es obligatorio")
        String contrasena) {
}
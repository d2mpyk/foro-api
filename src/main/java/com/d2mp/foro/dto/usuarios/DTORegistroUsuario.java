package com.d2mp.foro.dto.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTORegistroUsuario(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @Email
        @NotNull(message = "El email es obligatorio")
        String email,
        @NotBlank(message = "La contraseña es obligatorio")
        String contrasena,
        @NotBlank(message = "el perfil es obligatorio")
        String perfil) {

    public DTORegistroUsuario(String nombre, String email, String contrasena, String perfil) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.perfil = perfil;
    }
}

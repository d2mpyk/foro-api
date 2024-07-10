package com.d2mp.foro.domain.dto.usuarios;

import com.d2mp.foro.domain.enums.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DTORegistroUsuario(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotNull(message = "El email es obligatorio")
        @Email(message="Por favor ingrese un email válido")
        @Pattern(regexp=".+@.+\\..+", message="Por favor ingrese un email válido")
        String email,
        @NotBlank(message = "La contraseña es obligatorio")
        String contrasena,
        String perfil) {

    public DTORegistroUsuario(String nombre, String email, String contrasena, String perfil) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        if (perfil == null || perfil.isEmpty())
            this.perfil = String.valueOf(Perfil.ESTUDIANTE);
        else this.perfil = String.valueOf(Perfil.fromString(perfil));
    }
}

package com.d2mp.foro.domain.dto.usuarios;

import jakarta.validation.constraints.NotNull;

public record DTOActualizarUsuarios(
        @NotNull(message = "El ID del usuario es obligatorio")
        Long id,
        String nombre,
        String email,
        String contrasena,
        String perfil) {

}

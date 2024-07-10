package com.d2mp.foro.dto.usuarios;

public record DTOActualizarUsuarios(
        Long id,
        String nombre,
        String email,
        String contrasena,
        String perfil) {

}

package com.d2mp.foro.dto.usuarios;

import com.d2mp.foro.enums.Perfil;

public record DTOActualizarUsuarios(
        Long id,
        String nombre,
        String email,
        String contrasena,
        Perfil perfil) {

}

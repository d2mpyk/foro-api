package com.d2mp.foro.domain.dto.usuarios;

import com.d2mp.foro.domain.enums.Perfil;
import com.d2mp.foro.domain.model.Usuario;

public record DTOListarUsuarios(
        Long id,
        String nombre,
        String email,
        Perfil perfil) {

    public DTOListarUsuarios(Usuario usuario){
        this(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getEmail(),
            usuario.getPerfil()
        );
    }
}

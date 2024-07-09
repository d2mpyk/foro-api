package com.d2mp.foro.dto.usuarios;

import com.d2mp.foro.enums.Perfil;
import com.d2mp.foro.model.usuarios.Usuario;

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

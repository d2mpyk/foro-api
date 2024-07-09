package com.d2mp.foro.service.usuarios;

import com.d2mp.foro.dto.usuarios.DTOListarUsuarios;
import com.d2mp.foro.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Page<DTOListarUsuarios> listarUsuarios(Pageable pageable){
        return usuarioRepository.findAll(pageable).map(DTOListarUsuarios::new);
    }

    public Optional<DTOListarUsuarios> listarUsuario(Long id) {
        return usuarioRepository.findById(id).map(DTOListarUsuarios::new);
    }
}

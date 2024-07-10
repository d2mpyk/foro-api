package com.d2mp.foro.domain.service;

import com.d2mp.foro.domain.dto.usuarios.DTOActualizarUsuarios;
import com.d2mp.foro.domain.dto.usuarios.DTOListarUsuarios;
import com.d2mp.foro.domain.dto.usuarios.DTORegistrarUsuario;
import com.d2mp.foro.infra.errores.IntegrityCheck;
import com.d2mp.foro.domain.model.Usuario;
import com.d2mp.foro.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<DTOListarUsuarios> listarUsuarios(Pageable pageable){
        return usuarioRepository.findByActivoTrue(pageable).map(DTOListarUsuarios::new);
    }

    public Optional<DTOListarUsuarios> listarUsuario(Long id) {
        if (usuarioRepository.findById(id).isEmpty())
            throw new IntegrityCheck("El usuario no se encuentra registrado. Verifique el id.");
        return usuarioRepository.findById(id).map(DTOListarUsuarios::new);
    }

    public void eliminarUsuario(Long id) {
        if (usuarioRepository.findById(id).isEmpty())
            throw new IntegrityCheck("El usuario no se encuentra registrado. Verifique el id.");
        else usuarioRepository.deleteById(id);

    }

    public void desactivarUsuario(Long id) {
        if (usuarioRepository.findById(id).isEmpty())
            throw new IntegrityCheck("El usuario no se encuentra registrado. Verifique el id.");
        else {
            Usuario usuario = usuarioRepository.getReferenceById(id);
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
        }
    }

    public Optional<DTOListarUsuarios> actualizarUsuario(DTOActualizarUsuarios dtoActualizarUsuarios){
        if (usuarioRepository.findById(dtoActualizarUsuarios.id()).isEmpty())
            throw new IntegrityCheck("El usuario no se encuentra registrado. Verifique el id.");
        else {
            Usuario usuarioEncontrado = usuarioRepository.getReferenceById(dtoActualizarUsuarios.id());
            usuarioEncontrado.actualizarUsuario(dtoActualizarUsuarios);
            usuarioRepository.save(usuarioEncontrado);
            return Optional.of(new DTOListarUsuarios(usuarioEncontrado));
        }
    }

    public DTOListarUsuarios registrarUsuario(DTORegistrarUsuario dtoRegistrarUsuario) {
        if (usuarioRepository.findByEmail(dtoRegistrarUsuario.email()).isPresent())
            throw new IntegrityCheck("El usuario ya se encuentra registrado.");
        else {
            Usuario usuarioRegistro = new Usuario(dtoRegistrarUsuario);
            usuarioRepository.save(usuarioRegistro);
            return new DTOListarUsuarios(usuarioRegistro);
        }
    }
}

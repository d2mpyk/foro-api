package com.d2mp.foro.service.usuarios;

import com.d2mp.foro.dto.usuarios.DTOActualizarUsuarios;
import com.d2mp.foro.dto.usuarios.DTOListarUsuarios;
import com.d2mp.foro.dto.usuarios.DTORegistroUsuario;
import com.d2mp.foro.infra.errores.IntegrityCheck;
import com.d2mp.foro.model.usuarios.Usuario;
import com.d2mp.foro.repository.usuarios.UsuarioRepository;
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
        return usuarioRepository.findById(id).map(DTOListarUsuarios::new);
    }

    public void eliminarUsuario(Long id) {
        if (usuarioRepository.findById(id).isPresent())
            usuarioRepository.deleteById(id);
        else System.out.println("El usuario no se encuentra registrado");
    }

    public void desactivarUsuario(Long id) {
        if (usuarioRepository.findById(id).isPresent()){
            Usuario usuario = usuarioRepository.getReferenceById(id);
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
        }
        else System.out.println("El usuario no se encuentra registrado");
    }

    public DTOListarUsuarios actualizarUsuario(DTOActualizarUsuarios dtoActualizarUsuarios){
        Optional<Usuario> usuario = usuarioRepository.findById(dtoActualizarUsuarios.id());
        if (usuario.isPresent()){
            Usuario usuarioEncontrado = usuarioRepository.getReferenceById(usuario.get().getId());
            usuarioEncontrado.actualizarUsuario(dtoActualizarUsuarios);
            usuarioRepository.save(usuarioEncontrado);
            return new DTOListarUsuarios(usuarioEncontrado);
        } else {
            throw new IntegrityCheck("El usuario no existe. Verifique el id.");
        }
    }

    public DTOListarUsuarios registrarUsuario(DTORegistroUsuario dtoRegistroUsuario) {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByEmail(dtoRegistroUsuario.email()));
        if (usuario.isEmpty()){
            Usuario usuarioRegistro = new Usuario(dtoRegistroUsuario);
            usuarioRepository.save(usuarioRegistro);
            return new DTOListarUsuarios(usuarioRegistro);
        } else {
            throw new IntegrityCheck("El usuario ya se encuentra registrado.");
        }
    }
}

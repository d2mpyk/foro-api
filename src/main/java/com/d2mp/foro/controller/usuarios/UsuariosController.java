package com.d2mp.foro.controller.usuarios;

import com.d2mp.foro.dto.usuarios.DTOActualizarUsuarios;
import com.d2mp.foro.dto.usuarios.DTOListarUsuarios;
import com.d2mp.foro.dto.usuarios.DTORegistroUsuario;
import com.d2mp.foro.service.usuarios.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public Page<DTOListarUsuarios> listarUsuarios(Pageable pageable){
        return usuarioService.listarUsuarios(pageable);
    }
    @PostMapping("/{id}")
    public Optional<DTOListarUsuarios> listarUsuario(@PathVariable Long id){
        return usuarioService.listarUsuario(id);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void desactivarUsuario(@PathVariable Long id){
        usuarioService.desactivarUsuario(id);
    }
    @PutMapping
    @Transactional
    public DTOListarUsuarios actualizarUsuario(@RequestBody DTOActualizarUsuarios dtoActualizarUsuarios){
        return usuarioService.actualizarUsuario(dtoActualizarUsuarios);
    }
    @PostMapping
    public DTOListarUsuarios actualizarUsuario(@RequestBody DTORegistroUsuario dtoRegistroUsuario){
        return usuarioService.registrarUsuario(dtoRegistroUsuario);
    }
}

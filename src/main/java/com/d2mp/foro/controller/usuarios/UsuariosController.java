package com.d2mp.foro.controller.usuarios;

import com.d2mp.foro.dto.usuarios.DTOActualizarUsuarios;
import com.d2mp.foro.dto.usuarios.DTOListarUsuarios;
import com.d2mp.foro.dto.usuarios.DTORegistroUsuario;
import com.d2mp.foro.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<DTOListarUsuarios> listarUsuarios(Pageable pageable){
        return usuarioService.listarUsuarios(pageable);
    }
    @PostMapping("/{id}")
    public Optional<DTOListarUsuarios> listarUsuario(@PathVariable @Valid Long id){
        return usuarioService.listarUsuario(id);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void desactivarUsuario(@PathVariable @Valid Long id){
        usuarioService.desactivarUsuario(id);
    }
    @PutMapping
    @Transactional
    public Optional<DTOListarUsuarios> actualizarUsuario(@RequestBody @Valid DTOActualizarUsuarios dtoActualizarUsuarios){
        return usuarioService.actualizarUsuario(dtoActualizarUsuarios);
    }
    @PostMapping
    public DTOListarUsuarios registrarUsuario(@RequestBody @Valid DTORegistroUsuario dtoRegistroUsuario){
        return usuarioService.registrarUsuario(dtoRegistroUsuario);
    }
}

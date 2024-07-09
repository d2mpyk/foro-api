package com.d2mp.foro.controller.usuarios;

import com.d2mp.foro.dto.usuarios.DTOListarUsuarios;
import com.d2mp.foro.service.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
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
}

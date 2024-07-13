package com.d2mp.foro.controller;

import com.d2mp.foro.domain.dto.usuarios.DTOActualizarUsuarios;
import com.d2mp.foro.domain.dto.usuarios.DTOListarUsuarios;
import com.d2mp.foro.domain.dto.usuarios.DTORegistrarUsuario;
import com.d2mp.foro.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuariosController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<DTOListarUsuarios>> listarUsuarios(Pageable pageable){
        return ResponseEntity.ok(usuarioService.listarUsuarios(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DTOListarUsuarios>> listarUsuario(@PathVariable @Valid Long id){
        return ResponseEntity.ok(usuarioService.listarUsuario(id));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivarUsuario(@PathVariable @Valid Long id){
        usuarioService.desactivarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    @Transactional
    public ResponseEntity<Optional<DTOListarUsuarios>> actualizarUsuario(@RequestBody @Valid DTOActualizarUsuarios dtoActualizarUsuarios){
        Optional<DTOListarUsuarios> dtoListarUsuarios = usuarioService.actualizarUsuario(dtoActualizarUsuarios);
        URI url = UriComponentsBuilder.fromPath("/usuarios/{id}").buildAndExpand(dtoListarUsuarios.get().id()).toUri();
        return ResponseEntity.created(url).body(dtoListarUsuarios);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<DTOListarUsuarios> registrarUsuario(@RequestBody @Valid DTORegistrarUsuario dtoRegistrarUsuario){
        DTOListarUsuarios dtoListarUsuarios = usuarioService.registrarUsuario(dtoRegistrarUsuario);
        URI url = UriComponentsBuilder.fromPath("/usuarios/{id}").buildAndExpand(dtoListarUsuarios.id()).toUri();
        return ResponseEntity.created(url).body(dtoListarUsuarios);
    }
}

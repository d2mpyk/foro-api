package com.d2mp.foro.controller;

import com.d2mp.foro.domain.model.Usuario;
import com.d2mp.foro.infra.security.DTOAutenticacionUsuario;
import com.d2mp.foro.infra.security.DTOJWTToken;
import com.d2mp.foro.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DTOAutenticacionUsuario dtoAutenticacionUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                dtoAutenticacionUsuario.email(),
                dtoAutenticacionUsuario.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWT = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        authenticationManager.authenticate(authToken);
        return ResponseEntity.ok(new DTOJWTToken(JWT));
    }
}

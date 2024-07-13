package com.d2mp.foro.infra.security;

import com.d2mp.foro.domain.model.Usuario;
import com.d2mp.foro.domain.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository){
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Primero Obtenemos el token y lo validamos
        var header = request.getHeader("Authorization");
        if (header != null) {
            var token = header.replace("Bearer ","");
            if (tokenService.isValid(token)){
                String email = tokenService.getSubject(token);
                Usuario usuario = (Usuario) usuarioRepository.findByEmail(email);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities()); // Forzamos un inicio de sesión
                // Y pasamos el usuario al contexto de Spring Boot
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // Ejecuta el filtro y retorna al siguiente filtro.
        filterChain.doFilter(request,response);
    }
}

package com.d2mp.foro.infra.security;

import com.d2mp.foro.domain.repository.UsuarioRepository;
import com.d2mp.foro.infra.errores.IntegrityCheck;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public AuthenticationService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = usuarioRepository.findByEmail(username);
        if (userDetails != null)
            return userDetails;
        else throw new IntegrityCheck(STR."El usuario: \{username} no está registrado.");
    }
}

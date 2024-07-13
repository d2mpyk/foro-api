package com.d2mp.foro.domain.model;

import com.d2mp.foro.domain.dto.usuarios.DTOActualizarUsuarios;
import com.d2mp.foro.domain.dto.usuarios.DTORegistrarUsuario;
import com.d2mp.foro.domain.enums.Perfil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;
    @Enumerated(value = EnumType.STRING)
    private Perfil perfil;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Topico> topicos;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Respuesta> respuestas;
    private Boolean activo;

    public Usuario(DTORegistrarUsuario dtoRegistrarUsuario) {
        this.nombre = dtoRegistrarUsuario.nombre();
        this.email = dtoRegistrarUsuario.email();
        this.contrasena = dtoRegistrarUsuario.contrasena();
        if (dtoRegistrarUsuario.perfil() != null)
            this.perfil = Perfil.fromString(dtoRegistrarUsuario.perfil());
        else this.perfil = Perfil.ESTUDIANTE;
        this.activo = true;
    }
    public void actualizarUsuario(DTOActualizarUsuarios dtoActualizarUsuarios) {
        if (dtoActualizarUsuarios.nombre() != null)
            this.nombre = dtoActualizarUsuarios.nombre();
        if (dtoActualizarUsuarios.email() != null)
            this.email = dtoActualizarUsuarios.email();
        if (dtoActualizarUsuarios.contrasena() != null)
            this.contrasena = dtoActualizarUsuarios.contrasena();
        if (dtoActualizarUsuarios.perfil() != null)
            this.perfil = Perfil.fromString(dtoActualizarUsuarios.perfil());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        //
        switch (perfil) {
            case ADMINISTRADOR:
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            case ESTUDIANTE:
                authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
                break;
            case INSTRUCTOR:
                authorities.add(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"));
                break;
            case MODERADOR:
                authorities.add(new SimpleGrantedAuthority("ROLE_MODERATOR"));
                break;
        }
        return authorities;
    }
    @Override
    public String getUsername(){ return email; }
    @Override
    public String getPassword() {
        return contrasena;
    }
    @Override
    public boolean isAccountNonExpired() {
        return activo;
    }
    @Override
    public boolean isAccountNonLocked() {
        return activo;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return activo;
    }
    @Override
    public boolean isEnabled() {
        return activo;
    }
}

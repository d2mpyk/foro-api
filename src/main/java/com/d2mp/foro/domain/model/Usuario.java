package com.d2mp.foro.domain.model;

import com.d2mp.foro.domain.dto.usuarios.DTOActualizarUsuarios;
import com.d2mp.foro.domain.dto.usuarios.DTORegistrarUsuario;
import com.d2mp.foro.domain.enums.Perfil;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario {
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
}

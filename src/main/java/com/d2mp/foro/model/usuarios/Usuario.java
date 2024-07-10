package com.d2mp.foro.model.usuarios;

import com.d2mp.foro.dto.usuarios.DTOActualizarUsuarios;
import com.d2mp.foro.dto.usuarios.DTORegistroUsuario;
import com.d2mp.foro.enums.Perfil;
import jakarta.persistence.*;
import lombok.*;

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
    private Boolean activo;

    public Usuario(DTORegistroUsuario dtoRegistroUsuario) {
        this.nombre = dtoRegistroUsuario.nombre();
        this.email = dtoRegistroUsuario.email();
        this.contrasena = dtoRegistroUsuario.contrasena();
        this.perfil = Perfil.ESTUDIANTE;
        this.activo = true;
    }
    public void actualizarUsuario(DTOActualizarUsuarios dtoActualizarUsuarios) {
        if (dtoActualizarUsuarios.nombre() != null) this.nombre = dtoActualizarUsuarios.nombre();
        if (dtoActualizarUsuarios.email() != null) this.email = dtoActualizarUsuarios.email();
        if (dtoActualizarUsuarios.contrasena() != null) this.contrasena = dtoActualizarUsuarios.contrasena();
        if (dtoActualizarUsuarios.perfil() != null) this.perfil = dtoActualizarUsuarios.perfil();
    }
}

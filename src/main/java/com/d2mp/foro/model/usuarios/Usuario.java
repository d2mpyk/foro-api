package com.d2mp.foro.model.usuarios;

import com.d2mp.foro.enums.Perfil;
import jakarta.persistence.*;
import lombok.*;

@Entity
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
}

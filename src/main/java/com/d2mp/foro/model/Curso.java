package com.d2mp.foro.model;

import com.d2mp.foro.enums.Categoria;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Curso")
@Table(name = "cursos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}

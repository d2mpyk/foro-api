package com.d2mp.foro.domain.model;

import com.d2mp.foro.domain.dto.topicos.DTORegistrarTopico;
import com.d2mp.foro.domain.dto.topicos.DTOActualizarTopico;
import com.d2mp.foro.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "Topico")
@Table(name = "topicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario autor;
//    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    List<Respuesta> respuestas;

    public Topico(DTORegistrarTopico dtoRegistrarTopico, Curso curso, Usuario usuario){
        this.titulo = dtoRegistrarTopico.titulo();
        this.mensaje = dtoRegistrarTopico.mensaje();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.fecha_creacion = LocalDateTime.parse(now.format(formatter),formatter);
        this.status = Status.ABIERTO;
        this.curso = curso;
        this.autor = usuario;
    }
    public void actualizarTopico(DTOActualizarTopico dtoActualizarTopico, Curso curso, Usuario autor){
        if (dtoActualizarTopico.titulo() != null)
            this.titulo = dtoActualizarTopico.titulo();
        if (dtoActualizarTopico.mensaje() != null)
            this.mensaje = dtoActualizarTopico.mensaje();
        if (dtoActualizarTopico.fecha_creacion() != null)
            this.fecha_creacion = dtoActualizarTopico.fecha_creacion();
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.fecha_creacion = LocalDateTime.parse(now.format(formatter),formatter);
        }
        if (dtoActualizarTopico.status() != null )
            this.status = Status.fromString(dtoActualizarTopico.status());
        else this.status = Status.ABIERTO;
        if (curso != null)
            this.curso = curso;
        if (autor != null)
            this.autor = autor;
        if (dtoActualizarTopico.status() != null)
            this.status = Status.fromString(dtoActualizarTopico.status());
    }
}

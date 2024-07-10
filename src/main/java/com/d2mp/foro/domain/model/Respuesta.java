package com.d2mp.foro.domain.model;

import com.d2mp.foro.domain.dto.respuestas.DTOActualizarRespuesta;
import com.d2mp.foro.domain.dto.respuestas.DTORegistrarRespuesta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime fecha_creacion;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario autor;
    private Boolean solucion;

    public Respuesta(DTORegistrarRespuesta dtoRegistrarRespuesta, Topico topico, Usuario usuario){
        this.mensaje = dtoRegistrarRespuesta.mensaje();
        this.topico = topico;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.fecha_creacion = LocalDateTime.parse(now.format(formatter),formatter);
        this.autor = usuario;
        this.solucion = false;
    }

    public void actualizarRespuesta(DTOActualizarRespuesta dtoActualizarRespuesta, Topico topico, Usuario usuario){
        if (dtoActualizarRespuesta.mensaje() != null)
            this.mensaje = dtoActualizarRespuesta.mensaje();
        if (dtoActualizarRespuesta.topico_id() != null)
            this.topico = topico;
        if (dtoActualizarRespuesta.fecha_creacion() != null)
            this.fecha_creacion = dtoActualizarRespuesta.fecha_creacion();
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            this.fecha_creacion = LocalDateTime.parse(now.format(formatter),formatter);
        }
        this.autor = usuario;
        if (dtoActualizarRespuesta.solucion() != null)
            this.solucion = dtoActualizarRespuesta.solucion();
    }
}

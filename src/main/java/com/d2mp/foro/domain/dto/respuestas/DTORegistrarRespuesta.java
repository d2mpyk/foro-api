package com.d2mp.foro.domain.dto.respuestas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTORegistrarRespuesta(
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        @NotNull(message = "El id del tópico es obligatorio")
        Long topico_id,
        @NotNull(message = "El id del usuario es obligatorio")
        Long usuario_id,
        Boolean solucion) {

    public DTORegistrarRespuesta(String mensaje, Long topico_id, Long usuario_id, Boolean solucion){
        this.mensaje = mensaje;
        this.topico_id = topico_id;
        this.usuario_id = usuario_id;
        this.solucion = solucion;
    }
}

package com.d2mp.foro.domain.dto.respuestas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DTOActualizarRespuesta(
        @NotNull(message = "El id de la respuesta es obligatorio")
        Long id,
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        @NotNull(message = "El id del tópico es obligatorio")
        Long topico_id,
        LocalDateTime fecha_creacion,
        @NotNull(message = "El id del usuario es obligatorio")
        Long usuario_id,
        Boolean solucion) {


}

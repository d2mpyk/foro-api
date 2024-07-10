package com.d2mp.foro.dto.topicos;

import java.time.LocalDateTime;

public record DTOActualizarTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        String status,
        Long usuario_id,
        String curso) {

}

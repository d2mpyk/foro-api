package com.d2mp.foro.domain.dto.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DTOActualizarTopico(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        LocalDateTime fecha_creacion,
        String status,
        @NotNull
        Long usuario_id,
        String curso) {

}

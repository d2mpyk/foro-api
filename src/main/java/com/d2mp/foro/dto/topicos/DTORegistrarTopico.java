package com.d2mp.foro.dto.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTORegistrarTopico(
        @NotBlank(message = "El título es obligatorio")
        String titulo,
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        @NotNull(message = "El id del usuario es obligatorio")
        Long usuario_id,
        @NotBlank(message = "El curso es obligatorio")
        String curso){

    public DTORegistrarTopico(String titulo, String mensaje, Long usuario_id, String curso){
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.usuario_id = usuario_id;
        this.curso = curso;
    }
}

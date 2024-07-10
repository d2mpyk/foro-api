package com.d2mp.foro.domain.dto.topicos;

import com.d2mp.foro.domain.model.Topico;

import java.time.LocalDateTime;

public record DTOListarTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        String status,
        String curso,
        String autor) {

    public DTOListarTopicos(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_creacion(),
                topico.getStatus().toString(),
                topico.getCurso().getNombre(),
                topico.getAutor().getNombre());
    }
}

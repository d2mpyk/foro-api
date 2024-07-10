package com.d2mp.foro.domain.dto.respuestas;

import com.d2mp.foro.domain.model.Respuesta;

import java.time.LocalDateTime;

public record DTOListarRespuestas(
        Long id,
        String mensaje,
        String topico,
        LocalDateTime fecha_creacion,
        String usuario,
        Boolean solucion) {

    public DTOListarRespuestas(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(),
                respuesta.getFecha_creacion(),
                respuesta.getAutor().getNombre(),
                respuesta.getSolucion());
    }
}

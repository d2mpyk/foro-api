package com.d2mp.foro.domain.service;

import com.d2mp.foro.domain.dto.respuestas.DTOActualizarRespuesta;
import com.d2mp.foro.domain.dto.respuestas.DTOListarRespuestas;
import com.d2mp.foro.domain.dto.respuestas.DTORegistrarRespuesta;
import com.d2mp.foro.domain.model.Respuesta;
import com.d2mp.foro.domain.model.Topico;
import com.d2mp.foro.domain.model.Usuario;
import com.d2mp.foro.domain.repository.RespuestaRepository;
import com.d2mp.foro.domain.repository.TopicoRepository;
import com.d2mp.foro.domain.repository.UsuarioRepository;
import com.d2mp.foro.infra.errores.IntegrityCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<DTOListarRespuestas> listarRespuestas(Pageable pageable) {
        return respuestaRepository.findAll(pageable).map(DTOListarRespuestas::new);
    }

    public Optional<DTOListarRespuestas> listarRespuesta(Long id) {
        if (respuestaRepository.findById(id).isEmpty())
            throw new IntegrityCheck("La respuesta no se encuentra registrada, Verifique el id");
        return respuestaRepository.findById(id).map(DTOListarRespuestas::new);
    }

    public void eliminarRespuesta(Long id){
        if (respuestaRepository.findById(id).isEmpty())
            throw new IntegrityCheck("La respuesta no se encuentra registrada, Verifique el id");
        else respuestaRepository.deleteById(id);
    }

    public Optional<DTOListarRespuestas> registrarRespuesta(DTORegistrarRespuesta dtoRegistrarRespuesta) {
        if (topicoRepository.findById(dtoRegistrarRespuesta.topico_id()).isEmpty())
            throw new IntegrityCheck("El tópico no se encuentra registrado. Verifique el id");
        Optional<Topico> topico = topicoRepository.findById(dtoRegistrarRespuesta.topico_id());
        if(usuarioRepository.findById(dtoRegistrarRespuesta.usuario_id()).isEmpty())
            throw new IntegrityCheck("El usuario no se encuentra registrado. Verifique el id");
        Optional<Usuario> usuario = usuarioRepository.findById(dtoRegistrarRespuesta.usuario_id());
        Respuesta respuesta = new Respuesta(dtoRegistrarRespuesta, topico.get(), usuario.get());
        respuestaRepository.save(respuesta);
        return Optional.of(new DTOListarRespuestas(respuesta));
    }

    public Optional<DTOListarRespuestas> actualizarrespuesta(DTOActualizarRespuesta dtoActualizarRespuesta) {
        if (topicoRepository.findById(dtoActualizarRespuesta.topico_id()).isEmpty())
            throw new IntegrityCheck("El tópico no se encuentra registrado. Verifique el id");
        Optional<Topico> topico = topicoRepository.findById(dtoActualizarRespuesta.topico_id());
        if(usuarioRepository.findById(dtoActualizarRespuesta.usuario_id()).isEmpty())
            throw new IntegrityCheck("El usuario no se encuentra registrado. Verifique el id");
        Optional<Usuario> usuario = usuarioRepository.findById(dtoActualizarRespuesta.usuario_id());
        Respuesta respuestaActualizar = respuestaRepository.findById(dtoActualizarRespuesta.id()).get();
        respuestaActualizar.actualizarRespuesta(dtoActualizarRespuesta, topico.get(), usuario.get());
        return Optional.of(new DTOListarRespuestas(respuestaActualizar));
    }
}

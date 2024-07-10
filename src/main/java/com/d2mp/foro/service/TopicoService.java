package com.d2mp.foro.service;

import com.d2mp.foro.dto.topicos.DTOActualizarTopico;
import com.d2mp.foro.dto.topicos.DTOListarTopicos;
import com.d2mp.foro.dto.topicos.DTORegistrarTopico;
import com.d2mp.foro.infra.errores.IntegrityCheck;
import com.d2mp.foro.model.Curso;
import com.d2mp.foro.model.Topico;
import com.d2mp.foro.model.Usuario;
import com.d2mp.foro.repository.CursoRepository;
import com.d2mp.foro.repository.TopicoRepository;
import com.d2mp.foro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<DTOListarTopicos> listarTopicos(Pageable pageable) {
        return topicoRepository.findAll(pageable).map(DTOListarTopicos::new);
    }

    public Optional<DTOListarTopicos> listarTopico(Long id) {
        return topicoRepository.findById(id).map(DTOListarTopicos::new);
    }

    public Optional<DTOListarTopicos> registrarTopico(DTORegistrarTopico dtoRegistrarTopico) {
        if (cursoRepository.findByNombreContainsIgnoreCase(dtoRegistrarTopico.curso()).isEmpty())
            throw new IntegrityCheck("El curso no se encuentra registrado");
        Curso curso = cursoRepository.findByNombreContainsIgnoreCase(dtoRegistrarTopico.curso()).get();
        if (usuarioRepository.findById(dtoRegistrarTopico.usuario_id()).isEmpty())
            throw new IntegrityCheck("El usuario no se encuentra registrado");
        Usuario usuario = usuarioRepository.findById(dtoRegistrarTopico.usuario_id()).get();
        if (topicoRepository.findByTitulo(dtoRegistrarTopico.titulo()).isEmpty()){
            Topico topicoRegistro = new Topico(dtoRegistrarTopico, curso, usuario);
            topicoRepository.save(topicoRegistro);
            return Optional.of(new DTOListarTopicos(topicoRegistro));
        } else {
            throw new IntegrityCheck("El tópico ya se encuentra registrado");
        }
    }

    public Optional<DTOListarTopicos> actualizarTopico(DTOActualizarTopico dtoActualizarTopico){
        if (cursoRepository.findByNombreContainsIgnoreCase(dtoActualizarTopico.curso()).isEmpty())
            throw new IntegrityCheck("El curso no se encuentra registrado");
        Curso curso = cursoRepository.findByNombreContainsIgnoreCase(dtoActualizarTopico.curso()).get();
        if (usuarioRepository.findById(dtoActualizarTopico.usuario_id()).isEmpty())
            throw new IntegrityCheck("El usuario no se encuentra registrado");
        Usuario usuario = usuarioRepository.findById(dtoActualizarTopico.usuario_id()).get();
        if (topicoRepository.findByTitulo(dtoActualizarTopico.titulo()).isPresent())
            throw new IntegrityCheck("El tópico ya se encuentra registrado");
        Topico topicoActualizar = topicoRepository.getReferenceById(dtoActualizarTopico.id());
        topicoActualizar.actualizarTopico(dtoActualizarTopico, curso, usuario);
        topicoRepository.save(topicoActualizar);
        return Optional.of(new DTOListarTopicos(topicoActualizar));
    }

    public void eliminarTopico(Long id) {
        if (topicoRepository.findById(id).isEmpty())
            throw new IntegrityCheck("El tópico no se encuentra registrado");
        topicoRepository.deleteById(id);
    }
}

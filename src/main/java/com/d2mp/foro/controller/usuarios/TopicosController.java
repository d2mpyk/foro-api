package com.d2mp.foro.controller.usuarios;

import com.d2mp.foro.dto.topicos.DTOActualizarTopico;
import com.d2mp.foro.dto.topicos.DTOListarTopicos;
import com.d2mp.foro.dto.topicos.DTORegistrarTopico;
import com.d2mp.foro.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
    @Autowired
    TopicoService topicoService;

    @GetMapping
    public Page<DTOListarTopicos> listarTopicos(Pageable pageable){
        return topicoService.listarTopicos(pageable);
    }
    @PostMapping("/{id}")
    public Optional<DTOListarTopicos> listarTopico(@PathVariable @Valid Long id){
        return topicoService.listarTopico(id);
    }
    @PostMapping
    @Transactional
    public Optional<DTOListarTopicos> registrarTopico(@RequestBody @Valid DTORegistrarTopico dtoRegistrarTopico){
        return topicoService.registrarTopico(dtoRegistrarTopico);
    }
    @PutMapping
    @Transactional
    public Optional<DTOListarTopicos> actualizarTopico(@RequestBody @Valid DTOActualizarTopico dtoActualizarTopico){
        return topicoService.actualizarTopico(dtoActualizarTopico);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@RequestBody @Valid Long id){
        topicoService.eliminarTopico(id);
    }
}

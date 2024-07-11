package com.d2mp.foro.controller;

import com.d2mp.foro.domain.dto.topicos.DTOActualizarTopico;
import com.d2mp.foro.domain.dto.topicos.DTOListarTopicos;
import com.d2mp.foro.domain.dto.topicos.DTORegistrarTopico;
import com.d2mp.foro.domain.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
    @Autowired
    TopicoService topicoService;

    @GetMapping
    public ResponseEntity<Page<DTOListarTopicos>> listarTopicos(Pageable pageable){
        return ResponseEntity.ok(topicoService.listarTopicos(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DTOListarTopicos>> listarTopico(@PathVariable @Valid Long id){
        return ResponseEntity.ok(topicoService.listarTopico(id));
    }
    @PostMapping
    @Transactional
    public ResponseEntity<Optional<DTOListarTopicos>> registrarTopico(@RequestBody @Valid DTORegistrarTopico dtoRegistrarTopico){
        Optional<DTOListarTopicos> dtoListarTopicos = topicoService.registrarTopico(dtoRegistrarTopico);
        URI url = UriComponentsBuilder.fromPath("/topicos/{id}").buildAndExpand(dtoListarTopicos.get().id()).toUri();
        return ResponseEntity.created(url).body(dtoListarTopicos);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<Optional<DTOListarTopicos>> actualizarTopico(@RequestBody @Valid DTOActualizarTopico dtoActualizarTopico){
        Optional<DTOListarTopicos> dtoListarTopicos = topicoService.actualizarTopico(dtoActualizarTopico);
        URI url = UriComponentsBuilder.fromPath("/topicos/{id}").buildAndExpand(dtoListarTopicos.get().id()).toUri();
        return ResponseEntity.created(url).body(dtoListarTopicos);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@RequestBody @Valid Long id){
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}

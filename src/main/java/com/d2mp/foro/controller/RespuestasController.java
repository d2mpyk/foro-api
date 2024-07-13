package com.d2mp.foro.controller;

import com.d2mp.foro.domain.dto.respuestas.DTOActualizarRespuesta;
import com.d2mp.foro.domain.dto.respuestas.DTOListarRespuestas;
import com.d2mp.foro.domain.dto.respuestas.DTORegistrarRespuesta;
import com.d2mp.foro.domain.service.RespuestaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestasController {
    @Autowired
    private RespuestaService respuestaService;

    @GetMapping
    public ResponseEntity<Page<DTOListarRespuestas>> listarRespuestas(Pageable pageable){
        return ResponseEntity.ok(respuestaService.listarRespuestas(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DTOListarRespuestas>> listarRespuesta(@PathVariable @Valid Long id){
        return ResponseEntity.ok(respuestaService.listarRespuesta(id));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        respuestaService.eliminarRespuesta(id);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Optional<DTOListarRespuestas>> registrarRespuesta(@RequestBody @Valid DTORegistrarRespuesta dtoRegistrarRespuesta){
        Optional<DTOListarRespuestas> dtoListarRespuestas = respuestaService.registrarRespuesta(dtoRegistrarRespuesta);
        URI url = UriComponentsBuilder.fromPath("/respuestas/{id}").buildAndExpand(dtoListarRespuestas.get().id()).toUri();
        return ResponseEntity.created(url).body(dtoListarRespuestas);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Optional<DTOListarRespuestas>> actualizarRespuesta(@RequestBody @Valid DTOActualizarRespuesta dtoActualizarRespuesta){
        Optional<DTOListarRespuestas> dtoListarRespuestas = respuestaService.actualizarrespuesta(dtoActualizarRespuesta);
        URI url = UriComponentsBuilder.fromPath("/respuestas/{id}").buildAndExpand(dtoListarRespuestas.get().id()).toUri();
        return ResponseEntity.created(url).body(dtoListarRespuestas);
    }
}

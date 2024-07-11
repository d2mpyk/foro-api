package com.d2mp.foro.domain.repository;

import com.d2mp.foro.domain.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

}

package com.d2mp.foro.domain.repository;

import com.d2mp.foro.domain.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombreContainsIgnoreCase(String curso);
}

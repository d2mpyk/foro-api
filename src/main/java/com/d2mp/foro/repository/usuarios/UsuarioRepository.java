package com.d2mp.foro.repository.usuarios;

import com.d2mp.foro.dto.usuarios.DTOListarUsuarios;
import com.d2mp.foro.model.usuarios.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<DTOListarUsuarios> findByActivoTrue(Pageable pageable);
}

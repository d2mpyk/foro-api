ALTER TABLE respuestas
    ADD CONSTRAINT FK_respuestaUsuario
    FOREIGN KEY(usuario_id)
    REFERENCES usuarios(id) ON DELETE CASCADE,
    ADD CONSTRAINT FK_respuestaTopico
    FOREIGN KEY(topico_id)
    REFERENCES topicos(id) ON DELETE CASCADE;
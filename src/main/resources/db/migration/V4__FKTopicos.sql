ALTER TABLE topicos
    ADD CONSTRAINT FK_topicosUsuarios
        FOREIGN KEY(usuario_id)
        REFERENCES usuarios(id),
    ADD CONSTRAINT FK_topicosCursos
        FOREIGN KEY(curso_id)
        REFERENCES cursos(id);

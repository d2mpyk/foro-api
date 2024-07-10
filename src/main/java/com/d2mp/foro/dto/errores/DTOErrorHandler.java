package com.d2mp.foro.dto.errores;

import org.springframework.validation.FieldError;

public record DTOErrorHandler(
        String field,
        String error) {
    public DTOErrorHandler(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}

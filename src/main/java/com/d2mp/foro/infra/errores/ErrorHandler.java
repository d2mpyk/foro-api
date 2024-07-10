package com.d2mp.foro.infra.errores;

import com.d2mp.foro.dto.errores.DTOErrorHandler;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ErrorHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity ilegalArgument(Exception error){
        return ResponseEntity.badRequest().body(error.getMessage());
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity bussinesCheck(Exception error){
        return ResponseEntity.badRequest().body(error.getMessage());
    }
    @ExceptionHandler(IntegrityCheck.class)
    public ResponseEntity integrityCheck(Exception error){
        return ResponseEntity.badRequest().body(error.getMessage());
    }
    // POST blank fields
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400Handler(MethodArgumentNotValidException methodArgumentNotValidException){
        var errors = methodArgumentNotValidException.getFieldErrors().stream().map(DTOErrorHandler::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404Handler(){
        return ResponseEntity.notFound().build();
    }
}

package com.HealthCare.API.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidacioens(MethodArgumentNotValidException ex){
        Map<String,String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errores);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNoEncontrado(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleEnumParseException(HttpMessageNotReadableException ex) {
        if (ex.getMessage() != null && ex.getMessage().contains("Genero")) {
            return ResponseEntity.badRequest().body("El valor de 'genero' es inválido. Usa uno de: MASCULINO, FEMENINO, OTRO.");
        }
        return ResponseEntity.badRequest().body("Solicitud malformada.");
    }

}

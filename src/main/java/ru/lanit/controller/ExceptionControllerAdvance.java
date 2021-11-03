package ru.lanit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.lanit.exception.NotFoundException;
import ru.lanit.exception.InvalidDataException;

@ControllerAdvice
public class ExceptionControllerAdvance {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NotFound: " + e.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidData(InvalidDataException e) {
        return ResponseEntity.badRequest().body("InvalidData: " + e.getMessage());
    }
}
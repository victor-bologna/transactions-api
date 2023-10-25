package com.desafiopicpay.exception;

import com.desafiopicpay.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> treatDuplicateEntity(DataIntegrityViolationException dataIntegrityViolationException) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(exceptionDTO.statusCode()).body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundException> treatEntityNotFound(EntityNotFoundException entityNotFoundException) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ExceptionDTO> treatGeneralException(TransactionException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(exceptionDTO.statusCode()).body(exceptionDTO);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionDTO> treatGeneralException(UserException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(exceptionDTO.statusCode()).body(exceptionDTO);
    }
}

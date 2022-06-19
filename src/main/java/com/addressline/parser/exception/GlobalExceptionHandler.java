package com.addressline.parser.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AddressParserException.class)
    public ResponseEntity<String> addressException(RuntimeException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(ex.toString());
    }
}

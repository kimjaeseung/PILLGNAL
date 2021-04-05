package com.pillgnal.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    // 400
    @ExceptionHandler({
            UsernameNotFoundException.class,
    })
    public ResponseEntity BadRequestException(final RuntimeException ex) {
        // log.warn("error", ex);
        Map<String, String> msg = new HashMap<>();
        msg.put("code", "400");
        msg.put("msg", ex.getMessage());
        return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
    }

    // 401
//    @ExceptionHandler({ AccessDeniedException.class })
//    public ResponseEntity handleAccessDeniedException(final AccessDeniedException ex) {
//        log.warn("error", ex);
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
//    }

    // 500
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex) {
        log.info(ex.getClass().getName());
        log.error("error", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

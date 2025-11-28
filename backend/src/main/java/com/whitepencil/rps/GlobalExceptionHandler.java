package com.whitepencil.rps;

import com.whitepencil.rps.dto.ErrorResponseDto;
import com.whitepencil.rps.exception.PlayerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handlerGenericException(Exception ex) {
        log.error("Get Internal server error: " + ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponseDto(
                        "Internal server error",
                        ex.getMessage(),
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerPlayerIdNotFound(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(404)
                .body(new ErrorResponseDto(
                        "Player not found",
                        ex.getMessage(),
                        LocalDateTime.now()
                ));
    }

}

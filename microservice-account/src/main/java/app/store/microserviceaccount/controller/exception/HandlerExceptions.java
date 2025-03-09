package app.store.microserviceaccount.controller.exception;

import app.store.microserviceaccount.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class HandlerExceptions {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseDto uniqueConstraintViolationException(Exception e) {
        log.error("Error: {}", e.getMessage());
        return ResponseDto.builder()
                .message("Error: " + e.getMessage())
                .code("500")
                .httpStatus(500)
                .build();
    }
}

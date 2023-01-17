package me.akmaljon.rps.exception;

import me.akmaljon.rps.dto.ResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class})
    public HttpEntity<ResponseDto> handleException(RuntimeException ex) {
        ex.printStackTrace();
        ResponseDto responseData = new ResponseDto(500, "Internal Server Error | AKMALJON");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
    }

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ResponseDto> handleBadRequestException(ApiRequestException ex) {
        ex.printStackTrace();
        ResponseDto responseData = new ResponseDto(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(responseData);
    }
}

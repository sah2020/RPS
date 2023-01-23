package me.akmaljon.rps.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiRequestException {
    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}

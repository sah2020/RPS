package me.akmaljon.rps.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiRequestException {
    public BadRequestException(String body) {
        super(body, HttpStatus.BAD_REQUEST.value());
    }
}


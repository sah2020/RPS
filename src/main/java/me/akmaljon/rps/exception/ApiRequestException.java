package me.akmaljon.rps.exception;

public class ApiRequestException extends RuntimeException {
    private final int status;

    public ApiRequestException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
package me.akmaljon.rps.dto;

public class ResponseDto {
    private Integer statusCode;
    private String body;

    public ResponseDto() {
    }

    public ResponseDto(Integer statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public ResponseDto(String body) {
        this.statusCode = 200;
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

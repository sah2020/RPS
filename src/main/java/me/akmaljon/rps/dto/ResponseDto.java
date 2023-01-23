package me.akmaljon.rps.dto;

import java.io.Serializable;

public class ResponseDto implements Serializable {
    private Integer statusCode;
    private Object body;

    public ResponseDto() {
    }

    public ResponseDto(Integer statusCode, Object body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public ResponseDto(Object body) {
        this.statusCode = 200;
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}

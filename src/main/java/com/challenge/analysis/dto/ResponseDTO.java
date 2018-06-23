package com.challenge.analysis.dto;

import org.springframework.util.StringUtils;

public class ResponseDTO<T> {
    private Boolean status = true;
    private String message;
    private T data;

    public ResponseDTO() {
    }

    public ResponseDTO(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDTO(Boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;

    }

    public void setErrorResponse(Throwable e, String message) {
        this.message = StringUtils.isEmpty(message) ? e.getMessage() : message;
        this.status = false;
    }

    public void setFailureResponse(String message) {
        this.message = message;
        this.status = false;
    }

    void setSuccessResponse(T data, String message) {
        this.message = message;
        this.data = data;
        this.status = true;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}


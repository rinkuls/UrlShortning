package com.codingTest.urlShorteningService.model;

public class ErrorReponce {

    private String status;
    private String error;

    public ErrorReponce(String status, String error) {
        this.status = status;
        this.error = error;
    }

    public ErrorReponce() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

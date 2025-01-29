package com.netpipo.management.employee_management.controller;

import org.springframework.http.HttpStatus;

public class Message {
    private HttpStatus status;
    private String message;

    public Message() {
    }

    public Message(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

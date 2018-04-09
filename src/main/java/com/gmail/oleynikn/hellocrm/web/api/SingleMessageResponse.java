package com.gmail.oleynikn.hellocrm.web.api;

public class SingleMessageResponse {
    private String message;

    SingleMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
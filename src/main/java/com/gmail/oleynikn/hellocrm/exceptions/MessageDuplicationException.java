package com.gmail.oleynikn.hellocrm.exceptions;

public class MessageDuplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MessageDuplicationException(String message) {
        super(message);
    }

    public MessageDuplicationException(String message, Exception e) {
        super(message, e);
    }
}
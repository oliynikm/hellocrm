package com.gmail.oleynikn.hellocrm.exceptions;

public class MimeMessageException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MimeMessageException(String message) {
        super(message);
    }

    public MimeMessageException(String message, Exception e) {
        super(message, e);
    }
}
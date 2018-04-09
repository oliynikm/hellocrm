package com.gmail.oleynikn.hellocrm.exceptions;

public class MimeMessageConversionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MimeMessageConversionException(String message) {
        super(message);
    }

    public MimeMessageConversionException(String message, Exception e) {
        super(message, e);
    }
}
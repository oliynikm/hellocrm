package com.gmail.oleynikn.hellocrm.exceptions;

public class MailboxCommunicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MailboxCommunicationException(String message) {
        super(message);
    }

    public MailboxCommunicationException(String message, Exception e) {
        super(message, e);
    }
}
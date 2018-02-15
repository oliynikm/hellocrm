package com.gmail.oleynikn.hellocrm.service.mailbox;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.gmail.oleynikn.hellocrm.model.EmailMessage;

public class EmailMessageFactory {

    public static EmailMessage createFrom(Message message) {
        return createFrom((MimeMessage) message);
    }

    public static EmailMessage createFrom(MimeMessage message) {

        EmailMessage email = new EmailMessage();
        try {
            // TODO: save messageId
            email.setDescription(message.getSubject());
            email.setMessage(message);
        } catch (MessagingException e) {
            // TODO: log & throw
            e.printStackTrace();
        }
        return email;
    }
}

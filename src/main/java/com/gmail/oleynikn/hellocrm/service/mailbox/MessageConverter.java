package com.gmail.oleynikn.hellocrm.service.mailbox;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.gmail.oleynikn.hellocrm.model.EmailMessage;

public class MessageConverter {

    // TODO: rename to avoid ambiguity

    public static EmailMessage convertToEmailMessage(MimeMessage message) {

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

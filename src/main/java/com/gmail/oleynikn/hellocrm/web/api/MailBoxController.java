package com.gmail.oleynikn.hellocrm.web.api;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.oleynikn.hellocrm.service.mailbox.MailBoxService;

@RestController
@RequestMapping("/api/mailbox")
public class MailBoxController {

    private MailBoxService mailBoxService;

    @Autowired
    public MailBoxController(MailBoxService mailBoxService) {
        this.mailBoxService = mailBoxService;
    }

    @GetMapping
    public String receive() throws IOException {
        return "{\"message\":\"" + mailBoxService.receiveFromAllSessions() + "\"}";
    }

    @PostMapping
    public String send(@RequestBody Message mail)
            throws IOException, MessagingException {

        mailBoxService.sendEmail(mail.messageTo, mail.messageSubject, mail.message);
        return "{\"message\":\"Ok\"}";
    }


}

class Message {
    String messageTo;
    String messageSubject;
    String message;

    public String getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


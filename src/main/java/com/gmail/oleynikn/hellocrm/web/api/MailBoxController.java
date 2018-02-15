package com.gmail.oleynikn.hellocrm.web.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public void receive() throws IOException {
        mailBoxService.receiveAll();
    }
}



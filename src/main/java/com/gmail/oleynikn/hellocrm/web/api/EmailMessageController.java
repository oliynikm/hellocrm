package com.gmail.oleynikn.hellocrm.web.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.oleynikn.hellocrm.model.EmailMessage;
import com.gmail.oleynikn.hellocrm.service.EmailMessageService;

@RestController
@RequestMapping("/api/emails")
public class EmailMessageController {
    private final EmailMessageService emailService;

    @Autowired
    public EmailMessageController(EmailMessageService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public List<EmailMessage> getAll(HttpServletResponse response) {
        return emailService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        emailService.deleteById(id);
    }

    @GetMapping("/{id}")
    public EmailMessage getById(@PathVariable long id) {
        return emailService.findOne(id);
    }

    @PostMapping("/{id}")
    public EmailMessage save(@RequestBody EmailMessage message) {
        return emailService.save(message);
    }

    @GetMapping("/unassigned")
    public List<EmailMessage> getEmailsWithoutClient() {
        return emailService.findByClientId(null);
    }

    @GetMapping("/unread")
    public List<EmailMessage> getEmailsNew() {
        return emailService.findByState("New");
    }

}

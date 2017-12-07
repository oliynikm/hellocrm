package com.gmail.oleynikn.hellocrm.web.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.oleynikn.hellocrm.model.Email;
import com.gmail.oleynikn.hellocrm.service.email.EmailService;

@RestController
@RequestMapping("/api")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

  @GetMapping("/emails")

  public List<Email> getAllMails(HttpServletResponse response) {
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return emailService.findAll();
    }

}


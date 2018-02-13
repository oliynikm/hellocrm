package com.gmail.oleynikn.hellocrm.web.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.oleynikn.hellocrm.service.mailbox.MailBoxService;

@RestController
@RequestMapping("/api/mailbox")
public class MailBoxController {
    public MailBoxService mailBoxService;

    @Autowired
    public MailBoxController(MailBoxService mailBoxService) {
        this.mailBoxService = mailBoxService;
    }

    @GetMapping
    public Response refresh() throws IOException {
        mailBoxService.receiveAll();
        // TODO: return count of new messages
        return new Response();
    }

}

class Response {
    String message = "ok";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    String error = "0";
}

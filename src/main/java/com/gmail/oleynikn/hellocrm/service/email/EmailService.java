package com.gmail.oleynikn.hellocrm.service.email;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gmail.oleynikn.hellocrm.model.Email;

@Service
public class EmailService {


    public List<Email> findAll() {

        List<Email> emails = EmailUtils.receiveEmails();
        return emails;
    }
}

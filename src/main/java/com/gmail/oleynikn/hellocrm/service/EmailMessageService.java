package com.gmail.oleynikn.hellocrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.oleynikn.hellocrm.model.EmailMessage;
import com.gmail.oleynikn.hellocrm.repository.EmailMessageJpaRepository;

@Service
public class EmailMessageService {

    private EmailMessageJpaRepository emailRepository;

    @Autowired
    public void setEmailService(EmailMessageJpaRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<EmailMessage> findAll() {
        List<EmailMessage> emails = emailRepository.findAll();
        return emails;
    }

    public EmailMessage save(EmailMessage email) {
        return emailRepository.save(email);

    }

    public List<EmailMessage> findByClientId(Long id) {
        return emailRepository.findByClientId(id);
    }

    public void deleteById(long id) {
        emailRepository.delete(id);
    }

    public EmailMessage findOne(long id) {
        return emailRepository.findOne(id);
    }
}

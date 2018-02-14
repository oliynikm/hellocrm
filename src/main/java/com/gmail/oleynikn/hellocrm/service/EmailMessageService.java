package com.gmail.oleynikn.hellocrm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.oleynikn.hellocrm.model.EmailMessage;
import com.gmail.oleynikn.hellocrm.repository.EmailMessageRepository;

@Service
public class EmailMessageService {

    private EmailMessageRepository emailRepository;

    @Autowired
    public void setEmailService(EmailMessageRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<EmailMessage> findAll() {
        List<EmailMessage> emails = emailRepository.findAll();
        return emails;
    }

    @Transactional
    public EmailMessage save(EmailMessage email) {
        return emailRepository.save(email);
    }

    @Transactional
    public List<EmailMessage> saveAll(Iterable<EmailMessage> emails) {
        return emailRepository.save(emails);
    }

    public List<EmailMessage> findByClientId(Long id) {
        return emailRepository.findByClientId(id);
    }

    public List<EmailMessage> findByState(String state) {
        return emailRepository.findByState(state);
    }

    public void deleteById(long id) {
        emailRepository.delete(id);
    }

    public EmailMessage findOne(long id) {
        return emailRepository.findOne(id);
    }
}

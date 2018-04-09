package com.gmail.oleynikn.hellocrm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.oleynikn.hellocrm.exceptions.MessageDuplicationException;
import com.gmail.oleynikn.hellocrm.model.Client;
import com.gmail.oleynikn.hellocrm.model.EmailMessage;
import com.gmail.oleynikn.hellocrm.repository.EmailMessageRepository;

@Service
public class EmailMessageService {


    private EmailMessageRepository emailRepository;

    @Autowired
    public EmailMessageService(EmailMessageRepository emailRepository) {
        this.emailRepository = emailRepository;
    }



    public List<EmailMessage> findAll() {
        List<EmailMessage> emails = emailRepository.findAll();
        return emails;
    }

    @Transactional
    public EmailMessage save(EmailMessage email) {
        if (emailRepository.countByMessageId(email.getMessageId()) > 0) {
            throw new MessageDuplicationException("There is already a message with id = " + email.getMessageId());
        }
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

    public Long countByState(String state) {
        return emailRepository.countByState(state);
    }

    public Long countByClientId(Long clientId) {
        return emailRepository.countByClientId(clientId);
    }

    @Transactional
    public int setClientByAddress(Client client, String address) {
        return emailRepository.setClientForAddress(client, address);
    }

    @Transactional
    public EmailMessage update(EmailMessage message) {
        EmailMessage persistedMessage = emailRepository.findOne(message.getId());
        persistedMessage.updateFrom(message);
        return persistedMessage;
    }


}

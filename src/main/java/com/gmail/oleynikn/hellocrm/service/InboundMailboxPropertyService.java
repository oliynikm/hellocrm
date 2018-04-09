package com.gmail.oleynikn.hellocrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.oleynikn.hellocrm.model.InboundMailboxProperty;
import com.gmail.oleynikn.hellocrm.repository.InboundMailboxPropertyRepository;

@Service
public class InboundMailboxPropertyService {

    private InboundMailboxPropertyRepository mailboxPropertyRepository;

    @Autowired
    public InboundMailboxPropertyService(InboundMailboxPropertyRepository mailboxPropertyRepository) {
        this.mailboxPropertyRepository = mailboxPropertyRepository;
    }

    public List<InboundMailboxProperty> findAll() {
        List<InboundMailboxProperty> mailboxProperties = mailboxPropertyRepository.findAll();
        return mailboxProperties;
    }


    public void deleteById(long id) {
        mailboxPropertyRepository.delete(id);
    }

    public InboundMailboxProperty findOne(long id) {
        return mailboxPropertyRepository.findOne(id);
    }
}

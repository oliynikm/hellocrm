package com.gmail.oleynikn.hellocrm.service.mailbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailBoxService {

    private final MailboxManager mailboxManager;

    @Autowired
    public MailBoxService(MailboxManager mailboxManager) {
        this.mailboxManager = mailboxManager;
    }

    public void receiveAll() {
        mailboxManager.receiveAll();
    }
}

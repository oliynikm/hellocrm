package com.gmail.oleynikn.hellocrm.service.mailbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailBoxService {

    private final MailboxManager mailboxManager;

    @Autowired
    public MailBoxService(MailboxManager mailboxManager) {
        this.mailboxManager = mailboxManager;
    }

    @Scheduled(cron = "${mailbox.receive.cron}")
    public void receiveAll() {
        mailboxManager.receiveAll();
    }
}

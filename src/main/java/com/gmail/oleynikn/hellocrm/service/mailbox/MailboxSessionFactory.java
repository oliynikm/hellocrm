package com.gmail.oleynikn.hellocrm.service.mailbox;

import java.util.List;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailboxSessionFactory {

    private List<Session> sessions;

    // TODO: get session based on MailboxParametersRepository

    @Autowired
    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Session> getSessions() {
        return sessions;
    }

}

package com.gmail.oleynikn.hellocrm.service.mailbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gmail.oleynikn.hellocrm.exceptions.MailboxCommunicationException;
import com.gmail.oleynikn.hellocrm.model.InboundMailboxProperty;
import com.gmail.oleynikn.hellocrm.service.InboundMailboxPropertyService;

@Service
public class MailBoxService {

    private static final Logger LOG = Logger.getLogger(MailBoxService.class.getName());
    private List<Session> sessions;
    private JavaMailSender emailSender;
    private MimeMessageService messageService;
    private InboundMailboxPropertyService mailboxPropertyService;

    @Autowired
    public void setMailboxPropertyService(InboundMailboxPropertyService mailboxPropertyService) {
        this.mailboxPropertyService = mailboxPropertyService;
    }

    @Autowired
    public MailBoxService(MimeMessageService messageService) {
        this.messageService = messageService;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Scheduled(cron = "${mailbox.receive.cron}")
    public synchronized String receiveFromAllSessions() {

        int newMessageCount = 0;

        for (Session session : getSessions()) {
            newMessageCount += receiveEmails(session);
        }
        return "Received " + newMessageCount + " messages";
    }

    public void sendEmail(
            String to, String subject, String text) {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            message.saveChanges();
            messageService.saveOutbounddMessage(message);

            // don`t send in demo
            // emailSender.send(message);
        } catch (Exception e) {
            String errorMessage = "Exception while sending email";
            LOG.error(errorMessage, e);
        }

    }

    protected Integer receiveEmails(Session emailSession) {
        int newMessageCount = 0;
        try {
            Store store = emailSession.getStore();
            store.connect();

            Folder[] folders = store.getDefaultFolder().list();

            for (Folder folder : folders) {
                newMessageCount += receiveFromFolder(folder);
            }

            store.close();
            return newMessageCount;
        } catch (MessagingException | IOException e) {
            String errorMessage = "Exception while receiving emails";
            LOG.error(errorMessage, e);
            throw new MailboxCommunicationException(errorMessage, e);
        }
    }

    private int receiveFromFolder(Folder folder) throws MessagingException, IOException {

        int newMessageCount = 0;
        folder.open(Folder.READ_WRITE);

        if (folder.getUnreadMessageCount() > 0) {
            Message[] unreadMessages = folder.search(
                    new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            LOG.error(unreadMessages.length);
            newMessageCount = messageService.saveInboundMessages(unreadMessages).size();
        }
        folder.close(true);
        return newMessageCount;
    }

    private synchronized List<Session> getSessions() {

        if (this.sessions == null) {
            this.sessions = getInboundMailboxSessions();
        }

        return sessions;
    }

    private List<Session> getInboundMailboxSessions() {

        List<InboundMailboxProperty> mailboxProperties = mailboxPropertyService.findAll();

        Assert.notNull(mailboxProperties, "There is no configuration for inbound mailbox");

        List<Session> sessions = new ArrayList<>();
        for (InboundMailboxProperty mailboxProperty : mailboxProperties) {
            sessions.add(preparSession(mailboxProperty));
        }

        return sessions;
    }

    private Session preparSession(InboundMailboxProperty mailboxProperty) {
        Properties props = (Properties) System.getProperties().clone();
        props.setProperty("mail.store.protocol", mailboxProperty.getProtocol());
        props.setProperty("mail.host", mailboxProperty.getHost());

        Session emailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailboxProperty.getUser(), mailboxProperty.getPassword());
                    }
                });
        emailSession.setDebug(true);

        return emailSession;
    }

}

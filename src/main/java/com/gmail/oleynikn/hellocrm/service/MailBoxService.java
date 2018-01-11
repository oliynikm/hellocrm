package com.gmail.oleynikn.hellocrm.service;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.oleynikn.hellocrm.model.EmailMessage;

@Service
public class MailBoxService {

    private static final String DEFAULT_FOLDER = "INBOX";

    private EmailMessageService emailService;
    private Session emailSession;

    @Autowired
    public void setEmailService(EmailMessageService emailService) {
        this.emailService = emailService;
    }

  @Autowired
    public void setEmailSession(Session emailSession) {
        this.emailSession = emailSession;
  }

  public void refresh() {
        this.receiveEmails();
    }

    public void receiveEmails() {
        try {
            Store store = emailSession.getStore();
            store.connect();

            Folder folder = store.getFolder(DEFAULT_FOLDER);
            folder.open(Folder.READ_ONLY);
            if (haveUnreadMessages(folder)) {
                Message[] unreadMessages = folder.search(
                        new FlagTerm(new Flags(Flags.Flag.SEEN), false));

                for (Message m : unreadMessages) {
                    saveEmail(m);
                }
            }
            folder.close(true);
            store.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private boolean haveUnreadMessages(Folder folder) {
        // TODO: check if there are unread messages
        return true;
    }

    private void saveEmail(Message m) {
        MimeMessage recievedEmail = (MimeMessage) m;
        EmailMessage email = new EmailMessage();
        try {
            email.setDescription(recievedEmail.getSubject());
            email.setMessage(recievedEmail);

        } catch (MessagingException e) {

            // TODO: log & throw
            e.printStackTrace();
        }
        email = emailService.save(email);
        markAsRead(m);
    }

    private void markAsRead(Message email) {
        // TODO: mark email as red in storage
  }
}

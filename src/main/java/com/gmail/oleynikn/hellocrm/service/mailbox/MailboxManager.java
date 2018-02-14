package com.gmail.oleynikn.hellocrm.service.mailbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.oleynikn.hellocrm.model.Client;
import com.gmail.oleynikn.hellocrm.model.EmailMessage;
import com.gmail.oleynikn.hellocrm.service.ClientService;
import com.gmail.oleynikn.hellocrm.service.EmailMessageService;

@Service
public class MailboxManager {

    private static final String DEFAULT_FOLDER = "INBOX";
    private final EmailMessageService emailService;
    private final ClientService clientService;
    private final MailboxSessionFactory sessionFactory;

    @Autowired
    public MailboxManager(EmailMessageService emailService, ClientService clientService,
            MailboxSessionFactory sessionFactory) {
        this.emailService = emailService;
        this.sessionFactory = sessionFactory;
        this.clientService = clientService;
    }


    public void receiveAll() {
        // TODO: make it thread safe
        for (Session session : sessionFactory.getSessions()) {
            receiveEmails(session);
        }
    }

    protected void receiveEmails(Session emailSession) {
        try {
            Store store = emailSession.getStore();
            store.connect();

            Folder folder = store.getFolder(DEFAULT_FOLDER);
            folder.open(Folder.READ_WRITE);

            if (folder.getUnreadMessageCount() > 0) {

            Message[] unreadMessages = folder.search(
                        new FlagTerm(new Flags(Flags.Flag.SEEN), false));
                List<EmailMessage> emails = new ArrayList<>();
            for (Message message : unreadMessages) {
                    emails.add(toEmailMessage(message));
                }
                emailService.saveAll(emails);
            }

            folder.close(true);
            store.close();
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    protected EmailMessage toEmailMessage(Message message) throws MessagingException, IOException {
        message.setFlag(Flags.Flag.SEEN, true);
        EmailMessage emailMessage = MessageConverter.convertToEmailMessage(message);
        emailMessage.setClient(findClientbyEmail(((InternetAddress) emailMessage.getSender()).getAddress()));
        return emailMessage;
    }

    private Client findClientbyEmail(String address) {
        
        List<Client> clients=clientService.findByEmail(address);
        
        if(clients.isEmpty()||clients.size()>1) {
            return null; 
        }else {
        return clients.get(0);
        }
    }
}

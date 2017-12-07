package com.gmail.oleynikn.hellocrm.service.email;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import com.gmail.oleynikn.hellocrm.model.Email;

public class EmailUtils {
    private final static String HOST = "pop.i.ua";
    private final static String USER = "justforsometests";
    private final static String PASSWORD = "justforsometests1";
    private final static String PROTOCOL = "pop3";
    private static final String DEFAULT_FOLDER = "INBOX";

    public static List<Email> receiveEmails() {
        List<Email> emails = new ArrayList<>();

        Properties props = (Properties) System.getProperties().clone();
        Session emailSession = Session.getInstance(props, null);
        emailSession.setDebug(true);

        try {
            Store store = emailSession.getStore(PROTOCOL);
            store.connect(HOST, USER, PASSWORD);
            Folder folder = store.getFolder(DEFAULT_FOLDER);
            folder.open(Folder.READ_ONLY);
            Message[] unreadMessages = folder.search(
                    new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (Message m : unreadMessages) {
                emails.add(toEmail(m));
            }
            folder.close(false);
            store.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return emails;
    }

    private static Email toEmail(Message m) {

        Email email = new Email();
        try {
            email.setAddress(Arrays.asList(m.getFrom()).toString());
            email.setBody((String) m.getContent());
            email.setSubject(m.getSubject());
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

        return email;
    }

}

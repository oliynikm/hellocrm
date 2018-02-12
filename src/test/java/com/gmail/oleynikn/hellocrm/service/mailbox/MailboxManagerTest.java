package com.gmail.oleynikn.hellocrm.service.mailbox;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gmail.oleynikn.hellocrm.model.Client;
import com.gmail.oleynikn.hellocrm.model.EmailMessage;
import com.gmail.oleynikn.hellocrm.repository.ClientRepository;
import com.gmail.oleynikn.hellocrm.repository.EmailMessageRepository;
import com.gmail.oleynikn.hellocrm.service.ClientService;
import com.gmail.oleynikn.hellocrm.service.EmailMessageService;


@RunWith(MockitoJUnitRunner.class)
public class MailboxManagerTest {

    private static String testEmailAddress = "test@test.com";
    private static String testSubject = "Test Message";
    private static String testText = "Message Text Here \n with new line";
    private static Message message;
    private static Session session;
    @Mock
    private static EmailMessageRepository emailRepository;
    @Mock
    private static ClientRepository clientRepository;
    @Mock
    private static Client client;
    @InjectMocks
    private static MailboxSessionFactory mailboxSessionFactory;
    @InjectMocks
    private static EmailMessageService emailMessageService;
    @InjectMocks
    private static ClientService clientService;
    @Captor
    private static ArgumentCaptor<EmailMessage> emailArgument;
    @Captor
    private static ArgumentCaptor<String> clientEmailArgument;



    @BeforeClass
    public static void init() throws MessagingException{
        message = new MimeMessage(session);
        message.addFrom(new Address[] { new InternetAddress(testEmailAddress) });
        message.setSubject(testSubject);
        message.setText(testText);
    }


    @Test
    public void messageConvert() throws MessagingException, IOException {

        assertEquals(testEmailAddress,
                ((InternetAddress) MessageConverter.convertToEmailMessage(message).getSender()).getAddress());
        assertEquals(testSubject,
                MessageConverter.convertToEmailMessage(message).getDescription());
        assertEquals(testText,
                MessageConverter.convertToEmailMessage(message).getText());
    }

    @Test
    public void newMessageSave() throws MessagingException, IOException {
        ArrayList<Client> clients = new ArrayList<Client>();
        clients.add(client);
        when(clientRepository.findByEmail(testEmailAddress)).thenReturn(clients);

        MailboxManager mailboxManager = new MailboxManager(emailMessageService, clientService, mailboxSessionFactory);
        mailboxManager.saveReceivedMessage(message);
        verify(emailRepository).save(emailArgument.capture());
        assertEquals(testEmailAddress, ((InternetAddress) emailArgument.getValue().getSender()).getAddress());
        assertEquals(testSubject, emailArgument.getValue().getDescription());
        assertEquals(testText, emailArgument.getValue().getText());
        assertEquals(message, emailArgument.getValue().getMessage());
        assertEquals(client, emailArgument.getValue().getClient());


        verify(clientRepository).findByEmail(clientEmailArgument.capture());
        assertEquals(testEmailAddress, clientEmailArgument.getValue());
    }
}

package com.gmail.oleynikn.hellocrm.service.mailbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gmail.oleynikn.hellocrm.model.EmailMessage;
import com.gmail.oleynikn.hellocrm.service.ClientService;
import com.gmail.oleynikn.hellocrm.service.EmailMessageService;

@RunWith(MockitoJUnitRunner.class)
public class MimeMessageServiceTest {

    private static MimeMessage[] messages;
    private static MimeMessage message;

    @Mock
    private static EmailMessageService emailMessageService;
    @InjectMocks
    private static MimeMessageService mimeMessageService;

    @Mock
    private static ClientService clientService;

    @Captor
    private static ArgumentCaptor<String> addressArgument;

    @Captor
    private static ArgumentCaptor<EmailMessage> emailArgument;

    @Test
    public void testSaveMessagesEmptyArray() throws MessagingException, IOException {
        messages = new MimeMessage[] {};
        mimeMessageService.setClientService(clientService);
        mimeMessageService.saveInboundMessages(messages);
    }

    @Test
    public void testSaveMessagesOne() throws MessagingException, IOException {
        message = new MimeMessage(mock(MimeMessage.class));
        message.setFrom("test@test.com");
        messages = new MimeMessage[] { message };
        mimeMessageService.setClientService(clientService);
        mimeMessageService.setEmailService(emailMessageService);


        mimeMessageService.saveInboundMessages(messages);

        verify(emailMessageService).save(emailArgument.capture());
        assertNotNull(emailArgument.getValue());
        assertEquals(message, emailArgument.getValue().getMessage());

        verify(clientService).findByEmail(addressArgument.capture());

    }

}

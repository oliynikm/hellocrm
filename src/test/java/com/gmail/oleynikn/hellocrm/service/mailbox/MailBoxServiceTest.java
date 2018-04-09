package com.gmail.oleynikn.hellocrm.service.mailbox;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.mail.javamail.JavaMailSender;

@RunWith(MockitoJUnitRunner.class)
public class MailBoxServiceTest {

    @Mock
    private JavaMailSender emailSender;

    @Mock
    private MimeMessage message;

    @Mock
    private static MimeMessageService messageService;

    @InjectMocks
    private static MailBoxService mailBoxService;

    @Captor
    private static ArgumentCaptor<MimeMessage> messageArgument;
    


    @Test
    public void testSendEmail() throws MessagingException, IOException {

        when(emailSender.createMimeMessage()).thenReturn(message);
        mailBoxService.setEmailSender(emailSender);
        mailBoxService.sendEmail("wrw@ffewfe.ml", "dsfds", "dsfsdf");

        verify(messageService).saveOutbounddMessage(messageArgument.capture());
        assertEquals(message, messageArgument.getValue());

    }

}

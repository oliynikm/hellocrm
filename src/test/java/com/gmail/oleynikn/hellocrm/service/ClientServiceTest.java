package com.gmail.oleynikn.hellocrm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gmail.oleynikn.hellocrm.model.Client;
import com.gmail.oleynikn.hellocrm.repository.ClientRepository;
import com.gmail.oleynikn.hellocrm.repository.EmailMessageRepository;


@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    private static Client client;

    @Mock
    private static ClientRepository clientRepository;

    @Mock
    private static EmailMessageRepository emailRepository;

    @InjectMocks
    private static EmailMessageService emailMessageService;

    @InjectMocks
    private static ClientService clientService;

    @Captor
    private static ArgumentCaptor<Client> clientArgument;

    @Captor
    private static ArgumentCaptor<String> addressArgument;

    @BeforeClass
    public static void init() {
        client = new Client();
        client.setFirstName("John");
        client.setLastName("Smith");

    }

    @Test
    public void newClientSaveWithoutEmail() {

        when(clientRepository.save(client)).thenReturn(client);
        clientService.setEmailService(emailMessageService);

        clientService.save(client);
        verify(clientRepository).save(clientArgument.capture());
        assertEquals(client, clientArgument.getValue());
    }

    @Test
    public void newClientSaveWithEmail() {

        String email = "test@test.com";
        client.setEmail(email);
        when(clientRepository.save(client)).thenReturn(client);
        clientService.setEmailService(emailMessageService);

        clientService.save(client);
        verify(clientRepository).save(clientArgument.capture());
        assertEquals(client, clientArgument.getValue());

        verify(emailRepository).setClientForAddress(clientArgument.capture(), addressArgument.capture());
        assertEquals(client, clientArgument.getValue());
        assertEquals(email, addressArgument.getValue());
    }

}

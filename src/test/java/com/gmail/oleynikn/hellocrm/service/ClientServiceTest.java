package com.gmail.oleynikn.hellocrm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

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


@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
    private static Client client;

    @Mock
    private static ClientRepository clientRepository;
    @InjectMocks
    private static ClientService clientService;
    @Captor
    private static ArgumentCaptor<Client> argument;

    @BeforeClass
    public static void init(){
        client = new Client();
        client.setFirstName("John");
        client.setLastName("Smith");
    }

    @Test
    public void newClientSave() {
        clientService.save(client);
        verify(clientRepository).save(argument.capture());
        assertEquals(client, argument.getValue());
    }

}

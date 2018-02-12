package com.gmail.oleynikn.hellocrm.web.api;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gmail.oleynikn.hellocrm.model.Client;
import com.gmail.oleynikn.hellocrm.service.ClientService;

@RunWith(SpringRunner.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    private ClientController clientController;

    private static Client john;
    private static Client jane;
    private static List<Client> clients;

    @BeforeClass
    public static void initClass(){
        clients = new ArrayList<>();
        john = new Client();
        john.setFirstName("John");
        john.setLastName("Snow");
        john.setId(1L);
        jane = new Client();
        jane.setFirstName("Jane");
        jane.setLastName("White");
        jane.setId(2L);
        clients.add(jane);
        clients.add(john);
    }

    @Before
    public void init(){
        clientController = new ClientController(clientService, null);
        MockMvcBuilders.standaloneSetup(clientController).build();
        when(clientService.findAll()).thenReturn(clients);
        when(clientService.findOne(john.getId())).thenReturn(john);
    }

    @Test
    public void clientsAddedToModel(){
        assertThat(clientController.getAll(),
                allOf(hasItem(john), hasItem(jane)));

    }

    @Test
    public void oneClientAddedToModel(){
        assertThat(clientController.getById(1L), is(john));
    }

}

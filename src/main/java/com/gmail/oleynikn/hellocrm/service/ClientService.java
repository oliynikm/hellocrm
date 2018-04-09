package com.gmail.oleynikn.hellocrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.oleynikn.hellocrm.model.Client;
import com.gmail.oleynikn.hellocrm.repository.ClientRepository;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private EmailMessageService emailService;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setEmailService(EmailMessageService emailService) {
        this.emailService = emailService;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public void deleteById(long id) {
        clientRepository.delete(id);
    }

    public Client findOne(long id) {
        return clientRepository.findOne(id);
    }

    public Client getOne(long id) {
        return clientRepository.getOne(id);
    }

    public List<Client> findByFirsOrLasttName(String firstName, String lastName) {
        return clientRepository.findByFirstNameOrLastNameAllIgnoreCase(firstName, lastName);
    }

    public List<Client> findByEmail(String address) {
        return clientRepository.findByEmail(address);
    }

    public Client save(Client client) {
        Client persistedClient = clientRepository.save(client);
        linkEmails(persistedClient);
        return persistedClient;
    }

    public Client update(Client client) {
        Client persistedClient = clientRepository.getOne(client.getId());
        persistedClient.updateFrom(client);
        return persistedClient;
    }

    private void linkEmails(Client client) {
        String clientEmail = client.getEmail();
        if (null != clientEmail && clientEmail.length() > 6) {
            emailService.setClientByAddress(client, clientEmail);
        }
    }
}

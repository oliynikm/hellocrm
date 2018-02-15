package com.gmail.oleynikn.hellocrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.oleynikn.hellocrm.model.Client;
import com.gmail.oleynikn.hellocrm.repository.ClientRepository;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public void setEmailService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

  public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void deleteById(long id) {
        clientRepository.delete(id);
    }

    public Client findOne(long id) {
        return clientRepository.findOne(id);
    }

    public List<Client> findByFirsOrLasttName(String firstName, String lastName) {
        return clientRepository.findByFirstNameOrLastNameAllIgnoreCase(firstName, lastName);
    }

    public List<Client> findByEmail(String address) {
        return clientRepository.findByEmail(address);
    }

}

package com.gmail.oleynikn.hellocrm.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.oleynikn.hellocrm.model.Client;
import com.gmail.oleynikn.hellocrm.model.EmailMessage;
import com.gmail.oleynikn.hellocrm.service.ClientService;
import com.gmail.oleynikn.hellocrm.service.EmailMessageService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;
    private final EmailMessageService emailService;

    @Autowired
    public ClientController(ClientService clientService, EmailMessageService emailService) {
        this.clientService = clientService;
        this.emailService = emailService;
  }

    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
  }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        clientService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable long id) {
        return clientService.findOne(id);
    }

    @PostMapping("/{id}")
    public Client save(@RequestBody Client client) {
        Client persistedClient = clientService.findOne(client.getId());
        persistedClient.updateFrom(client);
        return persistedClient;
    }

  @PostMapping
  public Client add(@RequestBody Client client) {
    return clientService.save(client);
  }

    @GetMapping("/{id}/emails")
    public List<EmailMessage> getClientEmails(@PathVariable Long id) {
        return emailService.findByClientId(id);
    }

    @GetMapping("/{firstName}/{lastName}")
    public List<Client> getByFirsOrLasttName(@PathVariable String firstName, @PathVariable String lastName) {
        return clientService.findByFirsOrLasttName(firstName, lastName);
    }
}

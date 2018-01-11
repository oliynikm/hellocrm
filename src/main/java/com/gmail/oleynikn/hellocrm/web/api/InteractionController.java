package com.gmail.oleynikn.hellocrm.web.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.oleynikn.hellocrm.model.Interaction;
import com.gmail.oleynikn.hellocrm.service.InteractionService;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {
    private final InteractionService interactionService;

    @Autowired
    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @GetMapping
    public List<Interaction> getAll(HttpServletResponse response) {
        return interactionService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        interactionService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Interaction getById(@PathVariable long id) {
        return interactionService.findOne(id);
    }

    @PostMapping("/{id}")
    public Interaction save(@RequestBody Interaction message) {
        return interactionService.save(message);
    }

    @GetMapping("/unassigned")
    public List<Interaction> getEmailsWithoutClient(HttpServletResponse response) {
        return interactionService.findByClientId(null);
    }

}

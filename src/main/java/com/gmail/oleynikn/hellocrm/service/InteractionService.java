package com.gmail.oleynikn.hellocrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.oleynikn.hellocrm.model.Interaction;
import com.gmail.oleynikn.hellocrm.repository.InteractionJpaRepository;

@Service
public class InteractionService {

    private InteractionJpaRepository interactionRepository;

    @Autowired
    public void setEmailService(InteractionJpaRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    public List<Interaction> findAll() {
        List<Interaction> interactions = interactionRepository.findAll();
        return interactions;
    }

    public Interaction save(Interaction interaction) {
        return interactionRepository.save(interaction);

    }

    public List<Interaction> findByClientId(Long id) {
        return interactionRepository.findByClientId(id);
    }

    public void deleteById(long id) {
        interactionRepository.delete(id);
    }

    public Interaction findOne(long id) {
        return interactionRepository.findOne(id);
    }
}

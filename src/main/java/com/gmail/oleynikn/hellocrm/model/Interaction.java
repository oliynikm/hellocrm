package com.gmail.oleynikn.hellocrm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.gmail.oleynikn.hellocrm.web.api.jsonview.Views;

@Entity
@Inheritance
@DiscriminatorColumn(name = "TYPE")
public class Interaction {
    @Id
    @GeneratedValue
    @JsonView(Views.ListView.class)
    private Long id;

    @JsonView(Views.ListView.class)
    @Column(name = "TYPE", insertable = false, updatable = false)
    private String interactionType;

    // TODO:Add start date
    @Transient // temporary
    @JsonView(Views.ListView.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Date created = new Date();


    @JsonView(Views.ListView.class)
    private String description;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}

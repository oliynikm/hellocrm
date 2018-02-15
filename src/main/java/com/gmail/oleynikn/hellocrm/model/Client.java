package com.gmail.oleynikn.hellocrm.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String lastName;
    @NotNull
    private String firstName;
    @Email
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Interaction> interactions;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<EmailAddress> emailAddresses;

    public List<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<EmailAddress> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void updateFrom(Client source) {
        this.email = source.getEmail();
        this.firstName = source.getFirstName();
        this.lastName = source.getLastName();
    }

}

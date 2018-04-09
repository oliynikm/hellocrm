package com.gmail.oleynikn.hellocrm.model;

import javax.mail.internet.MimeMessage;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gmail.oleynikn.hellocrm.service.MimeMessageConverter;

@Entity
@DiscriminatorValue("EMAIL")
public class EmailMessage extends Interaction {

    private String clientAddress;

    @Column(unique = true)
    private String messageId;
    private String messageText;
    private String messageTextType;
    private String messageFrom;
    private String messageTo;

    @JsonIgnore
    @Column(columnDefinition = "blob")
    @Convert(converter = MimeMessageConverter.class)
    private MimeMessage message;

    public MimeMessage getMessage() {
        return message;
    }

    public void setMessage(MimeMessage message) {
        this.message = message;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String address) {
        this.clientAddress = address;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @JsonGetter("body")
    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @JsonGetter("bodyType")
    public String getMessageTextType() {
        return messageTextType;
    }

    public void setMessageTextType(String messageTextType) {
        this.messageTextType = messageTextType;
    }

    @JsonGetter("sender")
    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String from) {
        this.messageFrom = from;
    }

    @JsonGetter("recepient")
    public String getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(String to) {
        this.messageTo = to;
    }

    public void updateFrom(EmailMessage source) {
        this.setState(source.getState());
        this.setClient(source.getClient());
    }

}

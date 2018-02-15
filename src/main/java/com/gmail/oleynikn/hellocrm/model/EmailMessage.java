package com.gmail.oleynikn.hellocrm.model;

import java.io.IOException;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
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

    // TODO: add MessageID unique
    // TODO: check why MimeMessage different in insert and update statements
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

    @JsonGetter("body")
    public String getText() throws MessagingException, IOException {
        if (message != null) {
            return getText(message);
        }
        return "";
    }

    @JsonGetter("sender")
    public Address getSender() throws MessagingException, IOException {
        if (message != null) {
            return message.getFrom()[0];
        }
        return null;
    }

    private String getText(Part p) throws MessagingException, IOException {

        if (p.isMimeType("text/*")) {
            return (String) p.getContent();
        }

        if (p.isMimeType("multipart/alternative")) {

            Multipart multiPart = (Multipart) p.getContent();
            String text = null;
            for (int i = 0; i < multiPart.getCount(); i++) {

                Part bodyPart = multiPart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    if (text == null) {
                        text = getText(bodyPart);
                    }
                    continue;
                } else if (bodyPart.isMimeType("text/html")) {
                    String htmlText = getText(bodyPart);
                    if (htmlText != null)
                        return htmlText;
                } else {
                    return getText(bodyPart);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String partText = getText(mp.getBodyPart(i));
                if (partText != null)
                    return partText;
            }
        }
        return null;
    }

    public void updateFrom(EmailMessage source) {
        this.setState(source.getState());
        this.setClient(source.getClient());
    }

}

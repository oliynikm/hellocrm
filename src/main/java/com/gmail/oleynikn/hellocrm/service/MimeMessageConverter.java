package com.gmail.oleynikn.hellocrm.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MimeMessageConverter implements AttributeConverter<MimeMessage, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(MimeMessage message) {
        ByteArrayOutputStream tempMessageStream = new ByteArrayOutputStream();
        try {
            message.writeTo(tempMessageStream);
        } catch (IOException | MessagingException e) {
            // TODO: log and throw custom exception
            throw new RuntimeException(e);
        }
        return tempMessageStream.toByteArray();
    }

    @Override
    public MimeMessage convertToEntityAttribute(byte[] dbData) {
        if (dbData != null) {
            try {
                return new MimeMessage(null, new ByteArrayInputStream(dbData));
            } catch (MessagingException e) {
                // TODO: log and throw custom exception
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}

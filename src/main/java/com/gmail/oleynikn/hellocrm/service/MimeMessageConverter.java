package com.gmail.oleynikn.hellocrm.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

import com.gmail.oleynikn.hellocrm.exceptions.MimeMessageConversionException;

@Converter
public class MimeMessageConverter implements AttributeConverter<MimeMessage, byte[]> {
    private static final Logger LOG = Logger.getLogger(MimeMessageConverter.class.getName());


    @Override
    public byte[] convertToDatabaseColumn(MimeMessage message) {
        ByteArrayOutputStream tempMessageStream = new ByteArrayOutputStream();
        if (null != message) {
            try {
                message.writeTo(tempMessageStream);
            } catch (IOException | MessagingException e) {
                String errorMessage = "Can`t convert Mime Message " + message + " to byte arraye";
                LOG.error(errorMessage);
                throw new MimeMessageConversionException(errorMessage, e);
            }
        }
        return tempMessageStream.toByteArray();
    }

    @Override
    public MimeMessage convertToEntityAttribute(byte[] dbData) {
        if (dbData != null) {
            try {
                return new MimeMessage(null, new ByteArrayInputStream(dbData));
            } catch (MessagingException e) {
                String errorMessage = "Can`t convert byte array " + dbData + " to Mime Message";
                LOG.error(errorMessage);
                throw new MimeMessageConversionException(errorMessage, e);
            }
        }
        return null;
    }

}

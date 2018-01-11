package com.gmail.oleynikn.hellocrm.config;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailBoxConfig {

    @Bean
    @Autowired
    public Session prepareession(Environment env) {
        Properties props = (Properties) System.getProperties().clone();
        props.setProperty("mail.store.protocol", env.getProperty("mail.store.protocol"));
        props.setProperty("mail.host", env.getProperty("mail.host"));

        Session emailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                env.getProperty("mail.username"),
                                env.getProperty("mail.password"));
                    }
                });
        emailSession.setDebug(true);

        return emailSession;
    }

}

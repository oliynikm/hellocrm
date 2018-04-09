package com.gmail.oleynikn.hellocrm.service.mailbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gmail.oleynikn.hellocrm.exceptions.MessageDuplicationException;
import com.gmail.oleynikn.hellocrm.exceptions.MimeMessageException;
import com.gmail.oleynikn.hellocrm.model.Client;
import com.gmail.oleynikn.hellocrm.model.EmailMessage;
import com.gmail.oleynikn.hellocrm.model.Interaction;
import com.gmail.oleynikn.hellocrm.service.ClientService;
import com.gmail.oleynikn.hellocrm.service.EmailMessageService;

@Service
public class MimeMessageService {

    private static final Logger LOG = Logger.getLogger(MimeMessageService.class.getName());
    private final String demoAddress = "justforsometests@i.ua";

    public EmailMessageService emailService;
    public ClientService clientService;


    @Autowired
    public void setEmailService(EmailMessageService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<EmailMessage> saveInboundMessages(Message... messages) throws MessagingException, IOException {
        List<EmailMessage> emails = new ArrayList<>();

        for (Message message : messages) {
            MimeMessage mimeMessage = (MimeMessage) message;
            EmailMessage email = createEmailMessageFrom(mimeMessage);
            email.setDirection(Interaction.Direction.INBOUND);

            String clientAddress = getFirstAddress(mimeMessage.getFrom());
            email.setClientAddress(clientAddress);
            email.setMessageTo(demoAddress);
            email.setMessageFrom(clientAddress);
            linkWithClient(email);
            email.setState("New");

            try {
                emails.add(emailService.save(email));
            } catch (MessageDuplicationException e) {
                LOG.error("Error while saving email", e);
            }
        }
        return emails;
    }



    public EmailMessage saveOutbounddMessage(MimeMessage message) throws MessagingException, IOException {

        EmailMessage email = createEmailMessageFrom(message);
        email.setDirection(Interaction.Direction.OUTBOUND);

        String clientAddress = getFirstAddress(message.getAllRecipients());
        email.setClientAddress(clientAddress);
        email.setMessageTo(clientAddress);
        email.setMessageFrom(demoAddress);
        linkWithClient(email);
        email.setState("Sent");

        return emailService.save(email);

    }




    private EmailMessage createEmailMessageFrom(MimeMessage message) {

        EmailMessage email = new EmailMessage();
        try {
            email.setMessage(message);
            email.setMessageId(message.getMessageID());
            Date sentDate = message.getSentDate();
            if (sentDate != null) {
                email.setCreated(sentDate);
            } else {
                email.setCreated(new Date());
            }

            email.setDescription(message.getSubject());

            MessageBody body = getText(message);
            email.setMessageText(body.getText());
            email.setMessageTextType(body.getType());

        } catch (MessagingException | IOException e) {
            String errorMessage = "Exception while creating email message from message";
            LOG.error(errorMessage, e);
            throw new MimeMessageException(errorMessage, e);
        }
        return email;
    }

    private String getFirstAddress(Address[] toAddresses) {

        Assert.notNull(toAddresses, "There is no address");
        for (Address recipient : toAddresses) {
            if (recipient != null) {
                return ((InternetAddress) recipient).getAddress();
            }
        }
        return "";
    }

    private void linkWithClient(EmailMessage email) {
        List<Client> clients = clientService.findByEmail(email.getClientAddress());
        if (clients.size() == 1) {
            email.setClient(clients.get(0));
        }
    }

    private MessageBody getText(Part p) throws MessagingException, IOException {

        if (p.isMimeType("multipart/alternative") || p.getContent() instanceof Multipart) {

            Multipart multiPart = (Multipart) p.getContent();
            MessageBody textBody = null;
            for (int i = 0; i < multiPart.getCount(); i++) {

                Part bodyPart = multiPart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    if (textBody == null) {
                        textBody = getText(bodyPart);
                    }
                    continue;
                } else if (bodyPart.isMimeType("text/html")) {
                    String htmlText = (String) bodyPart.getContent();
                    if (htmlText != null) {
                        return new MessageBody(htmlText, "text/html");
                    }
                } else {
                    return getText(bodyPart);
                }
            }
            return textBody;

        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                MessageBody partText = getText(mp.getBodyPart(i));
                if (partText != null)
                    return partText;
            }
        }

        if (p.isMimeType("text/*")) {
            return new MessageBody((String) p.getContent(), p.getContentType());
        }

        return new MessageBody(null, null);
    }

}

class MessageBody {

    String text;
    String type;

    public MessageBody(String text, String type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
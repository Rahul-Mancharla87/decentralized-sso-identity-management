package com.global.system.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    Logger logger = LoggerFactory.getLogger(EmailService.class);

    public String sendMail(String toMail, String subject, String message1) {
        String to = toMail.trim();
        String from = "vertilinkjava@gmail.com";
        String host = "smtp.gmail.com";
        String msg = null;
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "ptwydgoclmavfpah");
            }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(message1);
            logger.info("sending....");
            Transport.send(message);
            logger.info("Sent Message Successfully...");
            msg = "Sent message successfully";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            logger.error("failed to send message");
            msg = "failed to send message";
        }
        return msg;

    }

}

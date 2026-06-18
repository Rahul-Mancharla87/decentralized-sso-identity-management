package com.cc09.cc09.config;

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

public class SendEmail {

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(SendEmail.class);
        String to = "shyam.9989@gmail.com";
        String from = "shyamjava2020@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "Shyam@2022");
            }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("This is the mail for registration");
            message.setText("You have successfully registered in our application");
            log.info("sending....");
            Transport.send(message);
            log.info("Sent message successfully");

        } catch (MessagingException mex) {
            mex.printStackTrace();
            log.error("message sending failed");
        }

    }

}

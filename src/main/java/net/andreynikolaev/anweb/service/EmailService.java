/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 *
 * @author andrey
 */
@Service
public class EmailService
{
 
    @Autowired
    private JavaMailSenderImpl mailSender;

    public void send(String from, String to, String subject, String message) {
        sendEmail(from,to,subject,message);
    }

    private void sendEmail(String from, String to, String subject, String bodyMessage){
        
        mailSender.send((MimeMessage mimeMessage) -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(bodyMessage, true);
        });
        
        
       // mailSender.send(mailMessage);
    }
}
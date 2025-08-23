package com.example.library.management.system.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;

    private static final String email="bibekmager746@gmail.com";

    public void sendMail(String message, String subject, String... to){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom(email);
        mailMessage.setTo(to);
    }
}

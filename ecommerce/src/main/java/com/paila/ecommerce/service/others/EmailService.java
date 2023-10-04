package com.paila.ecommerce.service.others;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService
{
    private final JavaMailSender javaMailSender;
    @Async
    public void sendEmail(String toEmail, String subject, String userMsg){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(userMsg);
        simpleMailMessage.setFrom("sandeshshrestha4313@gmail.com");
        javaMailSender.send(simpleMailMessage);
    }
}

package az.javidsadigli.portfolio.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import az.javidsadigli.portfolio.service.EmailService;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService
{
    private final JavaMailSender mailSender;

    @Value("${application.contact-information.email}")
    private String emailReceiver; 

    public void sendEmail(String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(emailReceiver);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

        log.debug("Email sent to {}", emailReceiver);
    }
}

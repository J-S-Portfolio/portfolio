package az.javidsadigli.portfolio.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import az.javidsadigli.portfolio.service.EmailService;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService
{
    @Value("${application.mailing.receiver}")
    private String emailReceiver; 

    @Value("${application.mailing.sender}")
    private String emailSender; 

    public void sendEmail(String subject, String body) 
    {


        log.info("Email sent to {}", emailReceiver);
    }
}

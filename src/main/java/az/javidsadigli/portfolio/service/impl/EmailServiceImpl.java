package az.javidsadigli.portfolio.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import sendinblue.ApiException;
import sendinblue.ApiResponse;
import sibApi.TransactionalEmailsApi;
import sibModel.CreateSmtpEmail;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

import az.javidsadigli.portfolio.service.EmailService;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService
{
    private final TransactionalEmailsApi transactionalEmailsApi; 

    @Value("${application.mailing.receiver.mail}")
    private String receiverMail; 

    @Value("${application.mailing.sender.mail}")
    private String senderMail; 

    @Value("${application.mailing.sender.name}")
    private String senderName; 

    public void sendEmail(String subject, String body) 
    {
        SendSmtpEmail email = new SendSmtpEmail();

        email.setTo(List.of(new SendSmtpEmailTo().email(receiverMail)));
        email.setSubject(subject);
        email.setTextContent(body);
        email.setSender(new SendSmtpEmailSender().name(senderName).email(senderMail));

        try
        {        
            ApiResponse<CreateSmtpEmail> response = transactionalEmailsApi.sendTransacEmailWithHttpInfo(email);
            log.info("" + response.getStatusCode());
        }
        catch(ApiException exception)
        {
            exception.printStackTrace();
        }

        log.info("Email sent to {}", receiverMail);
    }
}

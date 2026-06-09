package az.javidsadigli.portfolio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import az.javidsadigli.portfolio.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sendinblue.ApiException;
import sendinblue.ApiResponse;
import sibApi.TransactionalEmailsApi;
import sibModel.CreateSmtpEmail;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

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
            log.error("Code: {}", exception.getCode());
            log.error("Message: {}", exception.getMessage());
            log.error("ResponseBody: {}", exception.getResponseBody());
            log.error("ResponseHeaders: {}", exception.getResponseHeaders());
            
            log.error("An error happened while sending email : {}", exception.getMessage());
        }

        log.info("Email sent to {}", receiverMail);
    }
}

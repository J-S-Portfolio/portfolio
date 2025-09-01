package az.javidsadigli.portfolio.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import az.javidsadigli.portfolio.model.dto.request.SendEmailRequest;
import az.javidsadigli.portfolio.service.EmailService;
import az.javidsadigli.portfolio.service.SendEmailService;

@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService
{
    private final EmailService emailService;

    public void sendEmail(SendEmailRequest request) 
    {
        String subject = this.getEmailSubject(request);
        String body = this.buildEmailBody(request);

        emailService.sendEmail(subject, body);
    }   

    private String buildEmailBody(SendEmailRequest request)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("There is a new message from your portfolio.\n\n");

        if(isEmailValid(request.getUserEmail()))
            sb.append("User email: ").append(request.getUserEmail()).append("\n");

        if(request.getUserPhone() != null && !request.getUserPhone().isEmpty())
            sb.append("User phone: ").append(request.getUserPhone()).append("\n");

        sb.append("Message:\n").append(request.getBody()).append("\n\n");

        return sb.toString();
    }

    private String getEmailSubject(SendEmailRequest request)
    {
        return "New message from portfolio: " + request.getSubject();
    }

    private boolean isEmailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }
}

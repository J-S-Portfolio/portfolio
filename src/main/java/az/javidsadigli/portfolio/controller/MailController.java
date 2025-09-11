package az.javidsadigli.portfolio.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import az.javidsadigli.portfolio.model.dto.request.SendEmailRequest;
import az.javidsadigli.portfolio.service.SendEmailService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/mail")
public class MailController 
{
    private static final String LOG_TEMPLATE = "{} request to /api/mail{}";

    private final SendEmailService sendEmailService;

    @PostMapping(value = "/send-email")
    public void sendEmail(@RequestBody SendEmailRequest request)
    {
        log.info(LOG_TEMPLATE, "POST", "/send-email");
        sendEmailService.sendEmail(request);
    }
}

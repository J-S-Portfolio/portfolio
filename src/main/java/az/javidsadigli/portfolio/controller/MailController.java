package az.javidsadigli.portfolio.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import az.javidsadigli.portfolio.service.EmailService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/mail")
public class MailController 
{
    private static final String LOG_TEMPLATE = "{} request to /api/mail{}";

    private final EmailService emailService;

    @PostMapping(value = "/send-email")
    public void sendEmail(
        @RequestParam String subject, 
        @RequestParam String body)
    {
        log.info(
            LOG_TEMPLATE, "POST", 
            "/send-email?subject=" + subject + "&body=" + body);

        emailService.sendEmail(subject, body);
    }
}

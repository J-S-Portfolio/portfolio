package az.javidsadigli.portfolio.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import az.javidsadigli.portfolio.model.dto.request.SendEmailRequest;
import az.javidsadigli.portfolio.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSenderJob 
{
    private final SendEmailService sendEmailService; 

    @Scheduled(cron = "0 0 0 1 */3 *")
    private void execute()
    {
        log.debug("Job execution started.");
        this.sendTempEmail();
    }

    private void sendTempEmail()
    {
        SendEmailRequest request = SendEmailRequest.builder()
            .body("This is a temp message from your portfolio, to make email sender service active. Please ignore.")
            .subject("Temp Email")
            .userEmail("temp@email.com")
            .userFullName("Temp Email")
            .userPhone("Temp phone")
            .build();
            
        sendEmailService.sendEmail(request);
    }
}

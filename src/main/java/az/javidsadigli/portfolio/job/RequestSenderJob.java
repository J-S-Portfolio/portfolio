package az.javidsadigli.portfolio.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import az.javidsadigli.portfolio.client.RequestSenderClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestSenderJob 
{
    private final RequestSenderClient requestSenderClient;

    // @Scheduled(fixedRate = 60000)
    private void execute()
    {
        log.debug("Job execution started.");
        this.sendRequestToRequestSender();
    }

    private void sendRequestToRequestSender()
    {
        String response = requestSenderClient.getBaseUrl();
        log.debug("Request sender server response : {}", response);
    }
}

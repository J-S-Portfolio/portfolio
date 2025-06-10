package az.javidsadigli.portfolio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "request-sender-client", url = "${application.request-sender-url}")
public interface RequestSenderClient 
{
    @GetMapping(value = "/")
    public String getBaseUrl();    
}

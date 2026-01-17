package az.javidsadigli.portfolio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sibApi.TransactionalEmailsApi;

@Configuration
public class BrevoConfig 
{
    @Value("${application.mailing.api-key}")
    private String brevoApiKey; 

    @Bean
    public TransactionalEmailsApi brevoApi()
    {
        TransactionalEmailsApi api = new TransactionalEmailsApi();
        api.getApiClient().setApiKey(brevoApiKey);
        return api;
    }
}

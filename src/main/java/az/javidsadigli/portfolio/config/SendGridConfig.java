package az.javidsadigli.portfolio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendGridConfig 
{
    @Value("${spring.sendgrid.api-key}")
    private String sendGridApiKey; 

    @Bean
    public SendGrid sendGrid()
    {
        return new SendGrid(sendGridApiKey);
    }
}

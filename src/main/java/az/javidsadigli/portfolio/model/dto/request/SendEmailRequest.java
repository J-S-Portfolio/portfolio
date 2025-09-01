package az.javidsadigli.portfolio.model.dto.request;

import lombok.Data;

@Data
public class SendEmailRequest 
{
    private String subject; 
    private String body;
    private String userEmail; 
    private String userPhone;
}

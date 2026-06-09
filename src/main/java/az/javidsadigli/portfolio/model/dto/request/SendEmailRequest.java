package az.javidsadigli.portfolio.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendEmailRequest 
{
    private String subject; 
    private String body;
    private String userFullName; 
    private String userEmail; 
    private String userPhone;
}

package az.javidsadigli.portfolio.model.dto.response;

import lombok.Builder;
import lombok.Data;

import az.javidsadigli.portfolio.enums.CommandOutputType;

@Data
@Builder
public class BaseCommandResponse<T> 
{
    private T commandOutput;
    private CommandOutputType outputType; 
}

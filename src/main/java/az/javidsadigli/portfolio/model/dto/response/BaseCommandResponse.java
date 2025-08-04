package az.javidsadigli.portfolio.model.dto.response;

import lombok.Data;

import az.javidsadigli.portfolio.enums.CommandOutputType;

@Data
public class BaseCommandResponse<T> 
{
    private T commandOutput;
    private CommandOutputType outputType; 
}

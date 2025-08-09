package az.javidsadigli.portfolio.model.dto.response;

import lombok.Builder;
import lombok.Data;

import az.javidsadigli.portfolio.enums.CommandOutputType;
import az.javidsadigli.portfolio.model.dto.response.output.BaseCommandOutput;

@Data
@Builder
public class BaseCommandResponse<T extends BaseCommandOutput> 
{
    private T commandOutput;
    private CommandOutputType outputType; 
}

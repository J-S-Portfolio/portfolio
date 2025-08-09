package az.javidsadigli.portfolio.model.dto.response.output;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CommandNotFoundOutput extends BaseCommandOutput
{
    private String commandNotFoundMessage;
}

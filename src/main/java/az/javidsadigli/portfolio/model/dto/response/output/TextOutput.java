package az.javidsadigli.portfolio.model.dto.response.output;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TextOutput extends BaseCommandOutput
{
    private String text; 
}

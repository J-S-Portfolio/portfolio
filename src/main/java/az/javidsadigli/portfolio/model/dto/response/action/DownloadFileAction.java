package az.javidsadigli.portfolio.model.dto.response.action;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class DownloadFileAction extends BaseAction
{
    private String filePath; 
}

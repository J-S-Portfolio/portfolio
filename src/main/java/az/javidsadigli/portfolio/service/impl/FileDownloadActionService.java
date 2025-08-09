package az.javidsadigli.portfolio.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import az.javidsadigli.portfolio.enums.ActionType;
import az.javidsadigli.portfolio.model.dto.response.action.BaseAction;
import az.javidsadigli.portfolio.model.dto.response.action.DownloadFileAction;
import az.javidsadigli.portfolio.model.dto.response.output.ActionOutput;
import az.javidsadigli.portfolio.service.ActionExecutionService;

@Service
public class FileDownloadActionService implements ActionExecutionService
{
    @Value(value = "${application.file-paths.cv}")
    private String cvPath;

    @Value(value = "${application.file-names.cv}")
    private String cvName; 

    public ActionOutput<? extends BaseAction> executeAction(String command)
    {
        if (command.equals("cv")) 
            return ActionOutput.<DownloadFileAction>builder()
                    .action(
                        DownloadFileAction.builder()
                            .filePath(cvPath)
                            .fileName(cvName)
                            .build())
                    .actionType(ActionType.DOWNLOAD_FILE)
                    .build(); 

        return null; 
    }
}

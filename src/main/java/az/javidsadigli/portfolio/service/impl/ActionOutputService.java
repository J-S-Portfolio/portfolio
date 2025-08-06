package az.javidsadigli.portfolio.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import az.javidsadigli.portfolio.enums.CommandOutputType;
import az.javidsadigli.portfolio.model.dto.response.BaseCommandResponse;
import az.javidsadigli.portfolio.model.dto.response.action.BaseAction;
import az.javidsadigli.portfolio.model.dto.response.output.ActionOutput;
import az.javidsadigli.portfolio.service.CommandExecutionService;

@Service
@RequiredArgsConstructor
public class ActionOutputService implements CommandExecutionService
{
    private final GeneralActionsService generalActionsService; 

    @Override
    public BaseCommandResponse<ActionOutput<? extends BaseAction>> executeCommand(String command)
    {
        BaseCommandResponse<ActionOutput<? extends BaseAction>> commandResponse = 
            BaseCommandResponse.<ActionOutput<? extends BaseAction>>builder()
                .outputType(CommandOutputType.ACTION)
                .build(); 

        ActionOutput<? extends BaseAction> actionOutput;
         
        actionOutput = generalActionsService.executeAction(command); 
        if (actionOutput != null) 
        {
            commandResponse.setCommandOutput(actionOutput);
            return commandResponse; 
        }

        return null; 
    }
}

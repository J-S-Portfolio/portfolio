package az.javidsadigli.portfolio.service.impl;

import org.springframework.stereotype.Service;

import az.javidsadigli.portfolio.enums.ActionType;
import az.javidsadigli.portfolio.model.dto.response.action.BaseAction;
import az.javidsadigli.portfolio.model.dto.response.output.ActionOutput;
import az.javidsadigli.portfolio.service.ActionExecutionService;

@Service
public class SendEmailActionService implements ActionExecutionService
{
    @Override
    public ActionOutput<? extends BaseAction> executeAction(String command) 
    {
        if(command.equals("send-email"))
            return ActionOutput.<BaseAction>builder()
                    .actionType(ActionType.SEND_EMAIL)
                    .action(null)
                    .build();

        return null;
    }
}

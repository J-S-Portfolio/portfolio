package az.javidsadigli.portfolio.service;

import az.javidsadigli.portfolio.model.dto.response.action.BaseAction;
import az.javidsadigli.portfolio.model.dto.response.output.ActionOutput;

public interface ActionExecutionService 
{
    public ActionOutput<? extends BaseAction> executeAction(String command); 
}

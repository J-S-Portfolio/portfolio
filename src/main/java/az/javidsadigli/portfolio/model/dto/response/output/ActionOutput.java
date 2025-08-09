package az.javidsadigli.portfolio.model.dto.response.output;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import az.javidsadigli.portfolio.enums.ActionType;
import az.javidsadigli.portfolio.model.dto.response.action.BaseAction;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ActionOutput<T extends BaseAction> extends BaseCommandOutput
{
    private T action;
    private ActionType actionType; 
}

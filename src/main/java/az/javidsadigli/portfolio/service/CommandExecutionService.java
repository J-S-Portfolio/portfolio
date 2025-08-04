package az.javidsadigli.portfolio.service;

import az.javidsadigli.portfolio.model.dto.response.BaseCommandResponse;
import az.javidsadigli.portfolio.model.dto.response.output.BaseCommandOutput;

public interface CommandExecutionService<T extends BaseCommandOutput>
{
    public BaseCommandResponse<T> executeCommand(String command);
}

package az.javidsadigli.portfolio.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import az.javidsadigli.portfolio.enums.CommandOutputType;
import az.javidsadigli.portfolio.model.dto.response.BaseCommandResponse;
import az.javidsadigli.portfolio.model.dto.response.output.CommandNotFoundOutput;
import az.javidsadigli.portfolio.service.CommandExecutionService;
import az.javidsadigli.portfolio.storage.CommandStorage;

@Service
@RequiredArgsConstructor
public class CommandNotFoundService implements CommandExecutionService
{
    private final CommandStorage commandStorage; 

    @Override
    public BaseCommandResponse<CommandNotFoundOutput> executeCommand(String command)
    {
        return BaseCommandResponse.<CommandNotFoundOutput>builder()
                .commandOutput(
                    CommandNotFoundOutput.builder()
                        .commandNotFoundMessage(commandStorage.getCommandNotFoundOutput(command))
                        .build())
                .outputType(CommandOutputType.COMMAND_NOT_FOUND)
                .build(); 
    }
}

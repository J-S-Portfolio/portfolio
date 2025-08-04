package az.javidsadigli.portfolio.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import az.javidsadigli.portfolio.model.dto.response.BaseCommandResponse;
import az.javidsadigli.portfolio.model.dto.response.output.TextOutput;
import az.javidsadigli.portfolio.service.CommandExecutionService;
import az.javidsadigli.portfolio.storage.CommandStorage;

@Slf4j
@Service
@RequiredArgsConstructor
public class TextCommandOutputService implements CommandExecutionService<TextOutput>
{
    private final CommandStorage commandStorage; 

    @Override
    public BaseCommandResponse<TextOutput> executeCommand(String command)
    {
        String textOutput = commandStorage.getCommandOutput(command);

        if (textOutput == null) 
            return null; 

        return BaseCommandResponse.<TextOutput>builder()
                .commandOutput(
                    TextOutput.builder()
                        .text(textOutput)
                        .build())
                .build();
    }
}

package az.javidsadigli.portfolio.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import az.javidsadigli.portfolio.model.dto.response.BaseCommandResponse;
import az.javidsadigli.portfolio.model.dto.response.output.BaseCommandOutput;
import az.javidsadigli.portfolio.service.ConsoleService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsoleServiceImpl implements ConsoleService
{
    private final TextCommandOutputService textCommandOutputService; 

    public BaseCommandResponse<? extends BaseCommandOutput> executeCommand(String command)
    {
        log.debug("Request received for command : {}", command);

        BaseCommandResponse<? extends BaseCommandOutput> executionResponse; 
        
        executionResponse = textCommandOutputService.executeCommand(command); 

        if(executionResponse != null)
            return executionResponse; 
        
        return null;
    }
}

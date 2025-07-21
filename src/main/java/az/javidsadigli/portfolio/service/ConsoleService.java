package az.javidsadigli.portfolio.service;

import org.springframework.stereotype.Service;

import az.javidsadigli.portfolio.storage.ConsoleStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsoleService 
{
    private final ConsoleStorage consoleStorage; 

    public String getCommandOutput(String command)
    {
        log.debug("Request received for command : {}", command);
        return consoleStorage.getCommandOutput(command);
    }
}

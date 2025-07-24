package az.javidsadigli.portfolio.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import az.javidsadigli.portfolio.service.ConsoleService;
import az.javidsadigli.portfolio.storage.ConsoleStorage;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsoleServiceImpl implements ConsoleService
{
    private final ConsoleStorage consoleStorage; 

    public String executeCommand(String command)
    {
        log.debug("Request received for command : {}", command);
        return consoleStorage.getCommandOutput(command);
    }
}

package az.javidsadigli.portfolio.service;

import org.springframework.stereotype.Service;

import az.javidsadigli.portfolio.storage.ConsoleStorage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsoleService 
{
    private final ConsoleStorage consoleStorage; 

    public String getCommandOutput(String command)
    {
        return consoleStorage.getCommandOutput(command);
    }
}

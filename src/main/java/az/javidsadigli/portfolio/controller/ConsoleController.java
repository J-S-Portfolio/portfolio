package az.javidsadigli.portfolio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import az.javidsadigli.portfolio.service.ConsoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/console")
public class ConsoleController 
{
    private static final String LOG_TEMPLATE = "{} request to /api/v1/console{}";

    private final ConsoleService consoleService; 

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/command-output")
    public String getCommandOutput(@RequestParam String command)
    {
        log.info(LOG_TEMPLATE, "GET", "/command-output");
        return consoleService.getCommandOutput(command);
    }
}

package az.javidsadigli.portfolio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestSenderController 
{
    @GetMapping(value = "/test-server")
    public String testServer()
    {
        return "Portfolio server is on";
    }
}

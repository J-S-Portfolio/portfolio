package az.javidsadigli.portfolio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController 
{
    @GetMapping(value = "/")
    public String testUrl()
    {
        return "Welcome to Portfolio of JavidSadigli";
    }    
}

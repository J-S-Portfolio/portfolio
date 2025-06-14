package az.javidsadigli.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController 
{
    private static final String LOG_TEMPLATE = "{} request to {}";

    @GetMapping(value = "/")
    public String getHomePage(Model model)
    {
        log.info(LOG_TEMPLATE, "GET", "/");
        return "home";
    }
}

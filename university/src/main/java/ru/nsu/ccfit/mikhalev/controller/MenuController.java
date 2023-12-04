package ru.nsu.ccfit.mikhalev.controller;

import org.springframework.stereotype.Controller;
import ru.nsu.ccfit.mikhalev.api.MenuApi;

import static ru.nsu.ccfit.mikhalev.configuration.ApiHtml.HTML_MENU;

@Controller
public class MenuController implements MenuApi {

    @Override
    public String menu() { return HTML_MENU; }
}
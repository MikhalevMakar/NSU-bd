package ru.nsu.ccfit.mikhalev.api;

import org.springframework.web.bind.annotation.*;

import static ru.nsu.ccfit.mikhalev.configuration.PathApi.PATH_UNIVERSITY;

@RequestMapping(value = PATH_UNIVERSITY)
public interface MenuApi {
    @GetMapping
    String menu();
}
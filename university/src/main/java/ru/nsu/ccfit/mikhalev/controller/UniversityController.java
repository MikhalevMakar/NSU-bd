package ru.nsu.ccfit.mikhalev.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping(value = "/digital_library", produces = APPLICATION_JSON_VALUE)
public class UniversityController {



}

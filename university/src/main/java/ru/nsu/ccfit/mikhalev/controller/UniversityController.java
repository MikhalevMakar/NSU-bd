package ru.nsu.ccfit.mikhalev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.dao.entity.Student;


@Slf4j
@Validated
@Controller
@RequestMapping(value = "/")
public class UniversityController {

    @GetMapping
    public String showStudentForm(Model model) {
        log.info("show student form");
        model.addAttribute("student", new Student());
        return "student-form";
    }
}

package ru.nsu.ccfit.mikhalev.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.api.StudentApi;
import ru.nsu.ccfit.mikhalev.dto.StudentDto;
import ru.nsu.ccfit.mikhalev.exception.NoSuchGroupException;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;
import ru.nsu.ccfit.mikhalev.service.StudentService;

import static ru.nsu.ccfit.mikhalev.configuration.ApiHtml.*;

@Slf4j
@AllArgsConstructor
@Controller
public class StudentController implements StudentApi {

    @Qualifier("studentServiceImpl")
    private final StudentService studentService;

    public String showStudentForm(Model model, StudentDto student) {
        log.info("show student form");
        model.addAttribute("student", student);
        return HTML_STUDENT;
    }

    public String saveStudent(@ModelAttribute("student") StudentDto student) throws NoSuchGroupException,
                                                                                    UserExistsException {
        studentService.save(student);
        return HTML_REDIRECT_STUDENT;
    }
}
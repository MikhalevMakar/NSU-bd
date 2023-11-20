package ru.nsu.ccfit.mikhalev.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.nsu.ccfit.mikhalev.api.TeacherApi;
import ru.nsu.ccfit.mikhalev.dto.TeacherDto;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;
import ru.nsu.ccfit.mikhalev.service.TeacherService;

import static ru.nsu.ccfit.mikhalev.configuration.ApiHtml.*;

@Slf4j
@AllArgsConstructor
@Controller
public class TeacherController implements TeacherApi {

    @Qualifier("teacherServiceImpl")
    private final TeacherService teacherService;

    @Override
    public String showTeacherForm(Model model, TeacherDto teacher) {
        log.info("show student form");
        model.addAttribute("teacher", teacher);
        return HTML_TEACHER;
    }

    @Override
    public String saveTeacher(TeacherDto teacher) throws UserExistsException {
        teacherService.save(teacher);
        return HTML_REDIRECT_TEACHER;
    }
}
